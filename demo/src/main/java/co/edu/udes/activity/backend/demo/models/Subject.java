package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "Subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSubject;


       @Column(name = "name")
    private String name;

       @Column(name = "content")
    private String content;

       @Column(name = "objetives")
    private String objetives;

       @Column(name = "competencies")
    private String competencies;


    @ManyToOne
    @JoinColumn(name = "idPrerequisite")
    private Prerequisite prerequisite;

    @OneToMany(mappedBy = "subject")
    private List<Course> courses;
}