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
    @JoinColumn(name = "id_group", nullable = false)
    private Group group;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enrollmentDate", nullable = false)
    private Date enrollmentDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "qualification")
    private Double qualification;

    @Column(name = "P1_qualification")
    private Double p1Qualification;

    @Column(name = "P2_qualification")
    private Double p2Qualification;

    @Column(name = "P3_qualification")
    private Double p3Qualification;
}
