package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthenticationRequestDTO {

    private String sessionToken;
    private LocalDateTime expirationDate;
    private int failedAttempts;
    private boolean locked;
    private long userId;
}
