package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Entity
@Data
@Table(name = "Career")
public class Career {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCareer;

     @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    @OneToMany(mappedBy = "career")
    private List<Course> courses;
}