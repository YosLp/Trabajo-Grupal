package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import java.util.Date;

@Entity
@Data
@Table(name = "Attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAttendance;

    @Column(name = "localdate")
    private Date localdate;

        @Column(name = "status")
    private Boolean status;

        @ManyToOne
    @JoinColumn(name = "idSchedule", nullable = false)
    private Schedule schedule;


}
