package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name ="Permission")
public class Permission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(name ="name", unique = true, nullable = false)
    private String name;

    @Column(name="description")
    private String description;
   
    public Permission() {
    }

}
