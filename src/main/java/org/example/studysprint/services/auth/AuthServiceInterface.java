package org.example.studysprint.services.auth;

import org.example.studysprint.dto.auth.LoginRequest;
import org.example.studysprint.dto.auth.LoginResponse;
import org.example.studysprint.dto.auth.RegisterRequest;
import org.example.studysprint.dto.user.UserProfileResponse;

public interface AuthServiceInterface {
    void register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
    UserProfileResponse getCurrentUserProfile();

}
