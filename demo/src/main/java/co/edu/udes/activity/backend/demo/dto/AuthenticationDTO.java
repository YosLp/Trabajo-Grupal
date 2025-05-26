package co.edu.udes.activity.backend.demo.dto;

import co.edu.udes.activity.backend.demo.models.User;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AuthenticationDTO {

    private int failedAttempts;
    private boolean locked;
    private long userId;
    private String sessionToken;
    private LocalDateTime expirationDate;
    private String nombre;
    private String rol;
}
