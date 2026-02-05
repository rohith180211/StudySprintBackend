package org.example.studysprint.services.auth;

import org.example.studysprint.dto.auth.RegisterRequest;

public interface AuthServiceInterface {
    void register(RegisterRequest request);

}
