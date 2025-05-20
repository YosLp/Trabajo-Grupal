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
    @JoinColumn(name = "idStudent", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "idClass", nullable = false)
    private Class classes; // El nombre del campo es 'classes' (con 'c' minúscula)
}
