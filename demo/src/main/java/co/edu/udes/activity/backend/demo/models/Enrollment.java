package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "Enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnrollment;

    @ManyToOne
    @JoinColumn(name = "idStudent", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "idGroup", nullable = false)
    private Group group;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enrollmentDate", nullable = false)
    private Date enrollmentDate;

    @Column(name = "status", nullable = false)
    private String status;

    public Enrollment() {

    }
}
