package az.nexus.etherapybackend.dto.response;


import lombok.Builder;

@Builder
public record LoginResponse(
        String token,
        String email,
        String role
) {}