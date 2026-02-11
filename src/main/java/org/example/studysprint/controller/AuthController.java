package org.example.studysprint.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.studysprint.dto.auth.LoginRequest;
import org.example.studysprint.dto.auth.LoginResponse;
import org.example.studysprint.dto.auth.RegisterRequest;
import org.example.studysprint.dto.user.UserProfileResponse;
import org.example.studysprint.services.auth.AuthServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceInterface authService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile() {
        return ResponseEntity.ok(authService.getCurrentUserProfile());
    }


}
