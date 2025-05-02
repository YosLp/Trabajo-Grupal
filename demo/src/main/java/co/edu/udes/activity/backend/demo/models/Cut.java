package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
