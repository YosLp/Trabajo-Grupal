package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEnrollment;

    @ManyToOne
    @JoinColumn(name = "idStudent", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "idGroup", nullable = false)
    private Group group;
}
