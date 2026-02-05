package org.example.studysprint.services.auth.impl;

import lombok.RequiredArgsConstructor;
import org.example.studysprint.dto.auth.RegisterRequest;
import org.example.studysprint.model.Role;
import org.example.studysprint.model.User;
import org.example.studysprint.repository.UserRepository;
import org.example.studysprint.services.auth.AuthServiceInterface;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthServiceInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
}
