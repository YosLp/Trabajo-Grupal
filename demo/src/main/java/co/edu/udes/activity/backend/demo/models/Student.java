package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "idUser")
public class Student extends User {

    @Column(name = "address")
    private String address;

    @Column(name = "statusStudent")
    private String statusStudent;

    @Column(name = "registrationDate")
    private String registrationDate;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "Career_id", nullable = false)
    private Career career;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true) // Cambié "Student" a "student"
    private Set<Attendance> attendances = new HashSet<>();


    public Student() {
    }
}
