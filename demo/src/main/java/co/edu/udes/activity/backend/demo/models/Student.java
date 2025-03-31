package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "Student")
public class Student extends User {
    @Column(name = "enrollmentNumber", nullable = false, unique = true)
    private String enrollmentNumber;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "student")
    private List<AcademicRecord> academicRecords;
}