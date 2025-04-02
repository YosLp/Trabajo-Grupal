package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "Classroom")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idClassroom;

    @Column(name = "location")
    private String location;

     @Column(name = "capacity")
    private String capacity;



    @OneToMany(mappedBy = "classroom")
    private List<Class> classes;
}