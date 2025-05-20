package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id", nullable = false) // Añade esta columna en la tabla 'cut'
    private Semester semester; // ¡Nuevo campo para la relación con Semester!

    @ManyToOne
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    @Column (name="name")
    private String name;

    @Column (name="percentage")
    private double percentage;

    @Column(name = "cut_value", nullable = false)
    private Double cutValue;

}
