package co.edu.udes.activity.backend.demo.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name="Messaging")
public class Messaging {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="content")
    private String content;

    @Column(name="send_Date")
    private LocalDateTime sendDate;

    @Column(name="read")
    private boolean read;

    @Column(name="message_type")
    private String messageType;
    
    @ManyToOne
    @JoinColumn(name="sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name="receiver_id", nullable = false)
    private User receiver;
}
