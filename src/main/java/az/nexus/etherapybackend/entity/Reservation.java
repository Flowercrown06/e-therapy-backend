package az.nexus.etherapybackend.entity;

import az.nexus.etherapybackend.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservations")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long psychologistId;

    private LocalDate date;
    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}