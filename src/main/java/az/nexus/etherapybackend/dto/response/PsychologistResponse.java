package az.nexus.etherapybackend.dto.response;

import lombok.Builder;

@Builder
public record PsychologistResponse(
        Long id,
        String name,
        String surname,
        String specialization,
        Integer experience,
        String education,
        String bio,
        String languages,
        Double rating
) {}