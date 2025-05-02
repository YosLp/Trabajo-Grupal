package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "Subject")
public class Subject {
//ESTA ES LA ASIGNATURA O MATERIA PILAS QUE ES DIFERENTE DE LAS CLASES Y CURSOS
//vamos a eliminar la entidad course porq esa verga esta mal
// esta asignatura tiene un listado de grupos (que son cuantos grupos pueden salir de una misma asigntatura)
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

    @OneToMany(mappedBy = "Prerequisite")
    private List<Subject> prerequisite;

    @OneToMany(mappedBy = "groups")
    private List<Group> groups;
}