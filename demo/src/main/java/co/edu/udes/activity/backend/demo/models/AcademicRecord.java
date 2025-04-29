package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "AcademicRecord")
public class AcademicRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAcademicRecord;

    @Column(name = "academicHistory", length = 1000, nullable = false)
    private String academicHistory;


    @ManyToOne
    @JoinColumn(name = "idStudent", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "idGroup", nullable = false)
    private Group group;

    public AcademicRecord() {

    }
}
