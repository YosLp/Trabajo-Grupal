package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;


@Entity
@Data
@Table(name ="Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(name ="name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "permission")
    @ManyToMany
    @JoinTable(
        name="role_permission",
        joinColumns = @JoinColumn(name="role_id"),
        inverseJoinColumns = @JoinColumn(name= "permission_id"))
        private Set<Permission> permissions;
    
    public Role() {
    }
    
}
