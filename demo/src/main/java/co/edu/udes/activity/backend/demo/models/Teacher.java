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

    @Column(name = "institutional_code", nullable = false, unique = true)
    private String institutionalCode;

    @Column(name = "status_contract")
    private Boolean  statusContract;

    @OneToMany(mappedBy = "teacher")
    private List<Group> groups;

    @OneToMany(mappedBy = "teacher")
    private List<Evaluation> evaluations;
}
