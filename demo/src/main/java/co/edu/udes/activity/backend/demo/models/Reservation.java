package co.edu.udes.activity.backend.demo.models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name="Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column (name ="star_time")
    private LocalTime starTime;

    @Column(name="end_time")
    private LocalTime endTime;

    @Column (name="reservation_date")
    private LocalDate date;

    @Column (name="status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name="user_id", nullable =false)
    private User user;

    @ManyToOne
    @JoinColumn(name="space_id", nullable =false)
    private Space space;
}
