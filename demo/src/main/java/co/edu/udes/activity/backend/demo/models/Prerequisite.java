package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "Prerequisite")
public class Prerequisite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrerequisite;


     @Column(name = "subject")
    private String subject;

     @Column(name = "description")
    private String description;


    @OneToMany(mappedBy = "prerequisite")
    private List<Subject> subjects;
}