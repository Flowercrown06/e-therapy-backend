package az.nexus.etherapybackend.controller;


import az.nexus.etherapybackend.dto.request.PsychologistRequest;
import az.nexus.etherapybackend.dto.response.PsychologistResponse;
import az.nexus.etherapybackend.service.PsychologistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/psychologists")
@RequiredArgsConstructor
public class PsychologistController {

    private final PsychologistService psychologistService;


    @GetMapping
    public ResponseEntity<List<PsychologistResponse>> getAll() {
        return ResponseEntity.ok(psychologistService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<PsychologistResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(psychologistService.getById(id));
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> create(@RequestBody PsychologistRequest request) {
        psychologistService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody PsychologistRequest request) {
        psychologistService.update(id, request);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        psychologistService.delete(id);
        return ResponseEntity.noContent().build();
    }
}