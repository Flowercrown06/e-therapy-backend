package az.nexus.etherapybackend.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record ReservationResponse(
        Long id,
        String psychologistFullName,
        String userFullName,
        LocalDate date,
        LocalTime time,
        String status
) {}