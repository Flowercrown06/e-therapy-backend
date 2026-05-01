package az.nexus.etherapybackend.service;

import az.nexus.etherapybackend.dto.request.ReservationRequest;
import az.nexus.etherapybackend.dto.response.ReservationResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationService {
    List<LocalTime> getAvailableSlots(Long psychologistId, LocalDate date);
    void createReservation(ReservationRequest request);
    List<ReservationResponse> getMyReservations();
}