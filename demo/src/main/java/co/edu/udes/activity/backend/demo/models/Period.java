package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Period")
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPeriod;

    @Column(name = "name")
    private String name;

    @Column(name = "stardate")
    private Date stardate;

     @Column(name = "enddate")
    private Date enddate;



    @OneToMany(mappedBy = "period")
    private List<Course> courses;
}