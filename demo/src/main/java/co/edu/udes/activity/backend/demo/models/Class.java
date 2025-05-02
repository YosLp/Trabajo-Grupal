package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClass;

    @Column (name="fecha")
    private LocalDate fecha;

    @Column(name = "starHour")
    private LocalTime starHour;

    @Column(name = "endHour")
    private LocalTime endHour;

    @ManyToOne
    @JoinColumn(name = "idGroup", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "idClassroom", nullable = false)
    private Classroom classroom;

    @OneToMany(mappedBy = "Class", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Attendance> atttendances = new HashSet<>();
}