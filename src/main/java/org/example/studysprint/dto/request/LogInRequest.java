package org.example.studysprint.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
