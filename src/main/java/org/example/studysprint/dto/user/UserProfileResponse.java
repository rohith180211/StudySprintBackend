package org.example.studysprint.dto.user;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
    Long id;
    String name;
    String email;
    int points;
    int currentStreak;
}
