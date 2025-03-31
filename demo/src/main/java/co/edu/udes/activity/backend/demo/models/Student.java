package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Student")
@PrimaryKeyJoinColumn(name = "idStudent")
public class Student extends User {

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "statusStudent", length = 50, nullable = false)
    private String statusStudent;

    @Column(name = "registrationDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @Column(name = "phoneNumber", length = 20, nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "student")
    private List<AcademicRecord> academicRecords;

    @OneToMany(mappedBy = "student")
    private List<Report> reports;
}
