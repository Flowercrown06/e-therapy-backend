package az.nexus.etherapybackend.service;

import az.nexus.etherapybackend.dto.request.LoginRequest;
import az.nexus.etherapybackend.dto.request.RegisterRequest;
import az.nexus.etherapybackend.dto.response.LoginResponse;
import az.nexus.etherapybackend.entity.User;
import az.nexus.etherapybackend.enums.Role;
import az.nexus.etherapybackend.exception.AlreadyExistsException;
import az.nexus.etherapybackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegisterRequest request) {
        log.info("New registration attempt. Email: {}", request.email());

        userRepository.findByEmail(request.email())
                .ifPresent(u -> {
                    log.warn("Registration failed: Email already exists - {}", request.email());
                    throw new AlreadyExistsException("Email already exists!");
                });

        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        User savedUser = userRepository.save(user);

        log.info("User and subscription successfully created: {}", request.email());
    }

    public LoginResponse login(LoginRequest request) {
        log.info("Login attempt started. Email: {}", request.email());

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user);
        log.info("Login successful. Token generated for: {}", request.email());

        return LoginResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}