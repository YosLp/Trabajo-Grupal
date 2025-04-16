package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSchedule;


    @Column(name = "starHour")
    private Date starHour;

    @Column(name = "endHour")
    private Date endHour;

    @OneToMany(mappedBy = "schedule")
    private List<Class> classes;
}