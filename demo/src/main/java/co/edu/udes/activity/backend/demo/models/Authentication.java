package co.edu.udes.activity.backend.demo.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class Authentication {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name="session_token")
    private String sessionToken;

    @Column (name="expiration_date")
    private LocalDateTime  expirationDate;

    @Column (name="failedAttempts")
    private int failedAttempts;

    @Column (name="locked")
    private boolean locked;

    @ManyToOne
    @JoinColumn(name="user_id",nullable= false)
    private User user;
}
