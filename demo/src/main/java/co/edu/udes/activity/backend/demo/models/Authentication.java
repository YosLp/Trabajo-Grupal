package co.edu.udes.activity.backend.demo.models;

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
    private int sessionToken;

    @Column (name="expiration_date")
    private int expirationDate;

    @Column (name="failedAttempst")
    private int failedAttempst;

    @Column (name="locked")
    private boolean locked;

    @ManyToOne
    @JoinColumn(name="user_id",nullable= false)
    private User user;
}
