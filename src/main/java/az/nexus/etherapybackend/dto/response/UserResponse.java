package az.nexus.etherapybackend.dto.response;

import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String email,
        String fullName,
        String role
) {}