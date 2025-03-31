package co.edu.udes.activity.backend.demo.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table
public class Notification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="send_date")
    private LocalDateTime sendDate;

    @Column(name="read")
    private boolean read;

    @Column(name="message_type")
    private String messageType;

    @ManyToOne
    @JoinColumn(name="messaging_id", nullable =false)
    private Messaging messaging;

    @ManyToOne
    @JoinColumn(name="receiver_id", nullable = false)
    private User receiver;
}
