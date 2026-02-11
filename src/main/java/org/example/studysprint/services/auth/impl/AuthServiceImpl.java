package org.example.studysprint.services.auth.impl;

import lombok.RequiredArgsConstructor;
import org.example.studysprint.config.JwtUtil;
import org.example.studysprint.dto.auth.LoginRequest;
import org.example.studysprint.dto.auth.LoginResponse;
import org.example.studysprint.dto.auth.RegisterRequest;
import org.example.studysprint.dto.user.UserProfileResponse;
import org.example.studysprint.model.Role;
import org.example.studysprint.model.User;
import org.example.studysprint.repository.UserRepository;
import org.example.studysprint.services.auth.AuthServiceInterface;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthServiceInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Override
    public void register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .points(0)
                .currentStreak(0)
                .build();

        userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        String token = jwtUtil.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole().name()
        );

        return new LoginResponse(token);
    }

    @Override
    public UserProfileResponse getCurrentUserProfile() {
        Long userId = (Long) Objects.requireNonNull(SecurityContextHolder
                        .getContext()
                        .getAuthentication())
                .getPrincipal();

        assert userId != null;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserProfileResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .points(user.getPoints())
                .currentStreak(user.getCurrentStreak())
                .build();
    }
}
