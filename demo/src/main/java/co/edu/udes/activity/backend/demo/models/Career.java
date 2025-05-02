package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.ArrayList;


@Entity
@Data
@Table(name = "Career")
public class Career {
//ESTA ES LA CARRERA
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCareer;


   @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    @Column (name = "total_credits")
    private int totalcredits;

    @Column (name ="total_semesters")
    private int totalsemesters;

    @OneToMany(mappedBy = "career")
    private List<Subject> subjects = new ArrayList<>();

    @ManyToMany(mappedBy = "careers")
    private List<Semester> semesters = new ArrayList<>();
}