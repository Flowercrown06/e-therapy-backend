package az.nexus.etherapybackend.repository;

import az.nexus.etherapybackend.entity.Reservation;
import az.nexus.etherapybackend.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Psixoloqun həmin tarixdə olan bütün aktiv rezervasiyalarını gətirir
    List<Reservation> findAllByPsychologistIdAndReservationDateAndStatusNot(
            Long psychologistId,
            LocalDate date,
            ReservationStatus status
    );

    // İstifadəçinin öz rezervasiyalarını görməsi üçün
    List<Reservation> findAllByUserId(Long userId);
}