package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "Class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idClass;

    @Column(name = "starHour")
    private Date starHour;

    @Column(name = "endHour")
    private Date endHour;

    
    @Column(length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "idGroup", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "idSchedule", nullable = false)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "idClassroom", nullable = false)
    private Classroom classroom;
}