package co.edu.udes.activity.backend.demo.models;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table (name = "Space")
public class Space {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(name = "name")
    private String name;

    @Column (name="type")
    private String type;

    @Column (name="capacity")
    private int capacity;

    @Column (name ="available")
    private boolean available;

    public Space(){}
}
