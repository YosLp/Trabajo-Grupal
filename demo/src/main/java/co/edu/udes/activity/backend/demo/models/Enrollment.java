package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idStudent", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_group", nullable = false)
    private Group group;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enrollmentDate", nullable = false)
    private Date enrollmentDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "Final_qualification")
    private Double finalQualification;

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cut> cuts = new ArrayList<>();
}
