package az.nexus.etherapybackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PsychologistRequest(
        @NotBlank
        String name,

        @NotBlank
        String surname,

        @NotBlank
        String specialization,

        Integer experience,
        String education,
        String bio,
        String languages,

        @NotNull
        Long userId
) {}