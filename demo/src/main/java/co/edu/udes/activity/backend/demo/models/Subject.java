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
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "objetives")
    private String objetives;

    @Column(name = "competencies")
    private String competencies;

    // Relación con Career (ya la tenías y es correcta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCareer") // Cambiado a "idCareer"
    private Career career;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPrerequisite") // Cambiado a "idPrerequisite"
    private Prerequisite prerequisite;

    @ManyToMany
    @JoinTable(
            name = "subject_prerequisites",
            joinColumns = @JoinColumn(name = "subject_id"), // Correcto
            inverseJoinColumns = @JoinColumn(name = "prerequisite_id") // Correcto, pero debe referenciar a idSubject
    )
    private List<Subject> prerequisites;

    @ManyToMany(mappedBy = "prerequisites")
    private List<Subject> requiredBy;

    @OneToMany(mappedBy = "subject") // Asumiendo que Group tiene un campo 'subject'
    private List<Group> groups;

}

//ESTA ES LA ASIGNATURA O MATERIA PILAS QUE ES DIFERENTE DE LAS CLASES Y CURSOS
//vamos a eliminar la entidad course porq esa verga esta mal
// esta asignatura tiene un listado de grupos (que son cuantos grupos pueden salir de una misma asigntatura)