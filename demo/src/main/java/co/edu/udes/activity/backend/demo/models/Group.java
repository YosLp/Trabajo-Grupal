package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "Group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGroup;

    @Column(name = "studentsAmount", nullable = false)
    private int studentsAmount;

    @ManyToOne
    @JoinColumn(name = "idTeacher", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "idCourse", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "group")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "group")
    private List<Evaluation> evaluations;

    @OneToMany(mappedBy = "group")
    private List<Class> classes;

    @OneToMany(mappedBy = "group")
    private List<AcademicRecord> academicRecords;
}
