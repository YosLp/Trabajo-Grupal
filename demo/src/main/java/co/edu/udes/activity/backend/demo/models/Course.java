package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table (name="Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idcourse;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(name = "startdate")
    private Date startdate;

    @Column(name = "enddate")
    private Date enddate;

    @Column(length = 1000)
    private String content;

    @Column(name = "objetives")
    private String objetives;

    @Column(name = "competencies")
    private String competencies;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "status")
    private boolean status;


    @ManyToOne
    @JoinColumn(name = "idCareer", nullable = false)
    private Career career;

    @ManyToOne
    @JoinColumn(name = "idSubject", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "idPeriod", nullable = false)
    private Period period;

}