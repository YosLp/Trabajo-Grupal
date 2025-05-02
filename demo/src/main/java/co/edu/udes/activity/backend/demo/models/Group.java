package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "groupofstudents")
public class Group {

    @Id
    @Column(name = "id_group", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "studentsAmount", nullable = false)
    private int studentsAmount;

    @ManyToOne
    @JoinColumn(name = "idTeacher")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "Subject")
    private Subject subject;

    @OneToMany(mappedBy = "group")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "group")
    private List<Class> classes;

}
