package co.edu.udes.activity.backend.demo.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name="semester")
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name="name")
    private String name;

    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL) // 'semester' con 's' minúscula
    private List<Cut> cuts = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "semester_career",
            joinColumns = @JoinColumn(name = "semester_id"),
            inverseJoinColumns = @JoinColumn(name = "career_id")
    )
    private List<Career> careers = new ArrayList<>();
}
