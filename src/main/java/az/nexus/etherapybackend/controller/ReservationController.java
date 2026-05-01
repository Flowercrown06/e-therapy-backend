package az.nexus.etherapybackend.controller;


import az.nexus.etherapybackend.dto.request.ReservationRequest;
import az.nexus.etherapybackend.dto.response.ReservationResponse;
import az.nexus.etherapybackend.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    //   GET .../slots?psychologistId=1&date=2024-10-30
    @GetMapping("/slots")
    public ResponseEntity<List<LocalTime>> getAvailableSlots(
            @RequestParam Long psychologistId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(reservationService.getAvailableSlots(psychologistId, date));
    }


    @PostMapping
    public ResponseEntity<Void> createReservation(@RequestBody ReservationRequest request) {
        reservationService.createReservation(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/my")
    public ResponseEntity<List<ReservationResponse>> getMyReservations() {
        return ResponseEntity.ok(reservationService.getMyReservations());
    }
}