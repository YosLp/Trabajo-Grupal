package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "Teacher")
public class Teacher extends User {
    @Column(name = "specialization", nullable = false)
    private String specialization;

    @OneToMany(mappedBy = "teacher")
    private List<Group> groups;

    @OneToMany(mappedBy = "teacher")
    private List<Evaluation> evaluations;
}
