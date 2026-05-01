package az.nexus.etherapybackend.service.impl;

import az.nexus.etherapybackend.dto.request.ReservationRequest;
import az.nexus.etherapybackend.dto.response.ReservationResponse;
import az.nexus.etherapybackend.entity.Psychologist;
import az.nexus.etherapybackend.entity.Reservation;
import az.nexus.etherapybackend.enums.ReservationStatus;
import az.nexus.etherapybackend.mapper.ReservationMapper;
import az.nexus.etherapybackend.repository.PsychologistRepository;
import az.nexus.etherapybackend.repository.ReservationRepository;
import az.nexus.etherapybackend.auth.MyUserDetails;
import az.nexus.etherapybackend.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final PsychologistRepository psychologistRepository;
    private final ReservationMapper reservationMapper;

    @Override
    @Transactional(readOnly = true)
    public List<LocalTime> getAvailableSlots(Long psychologistId, LocalDate date) {

         List<Reservation> busyReservations = reservationRepository
                .findAllByPsychologistIdAndReservationDateAndStatusNot(
                        psychologistId, date, ReservationStatus.CANCELLED);

        List<LocalTime> busyTimes = busyReservations.stream()
                .map(Reservation::getReservationTime)
                .toList();

        // saat 9-18:00 arası boş slotları hesablayırıq
        List<LocalTime> allSlots = new ArrayList<>();
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(18, 0);

        while (startTime.isBefore(endTime)) {
            if (!busyTimes.contains(startTime)) {
                allSlots.add(startTime);
            }
            startTime = startTime.plusHours(1);
        }
        return allSlots;
    }

    @Override
    @Transactional
    public void createReservation(ReservationRequest request) {

        MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        Psychologist psychologist = psychologistRepository.findById(request.psychologistId())
                .orElseThrow(() -> new RuntimeException("Psixoloq tapılmadı"));

        validateAvailability(request);

        Reservation reservation = Reservation.builder()
                .user(currentUser.getUser())
                .psychologist(psychologist)
                .reservationDate(request.date())
                .reservationTime(request.time())
                .status(ReservationStatus.PENDING)
                .build();

        reservationRepository.save(reservation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationResponse> getMyReservations() {
        MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return reservationRepository.findAllByUserId(currentUser.getUserId())
                .stream()
                .map(reservationMapper::toResponse)
                .toList();
    }

    private void validateAvailability(ReservationRequest request) {
        boolean isAlreadyBooked = reservationRepository
                .findAllByPsychologistIdAndReservationDateAndStatusNot(
                        request.psychologistId(), request.date(), ReservationStatus.CANCELLED)
                .stream()
                .anyMatch(r -> r.getReservationTime().equals(request.time()));

        if (isAlreadyBooked) {
            throw new RuntimeException("Təəssüf ki, bu saat artıq tutulub.");
        }
    }
}