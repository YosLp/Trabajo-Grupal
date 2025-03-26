package co.edu.udes.activity.backend.demo.models;

import java.time.LocalDateTime;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name="Loan")
public class Loan {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column (name="loan_date")
    private LocalDateTime loanDate;

    @Column (name="return_date")
    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name ="student:_id", nullable= false)
    private Student student;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="loan_material",
    joinColumns = @JoinColumn(name ="loan_id"),
    inverseJoinColumns = @JoinColumn(name="material_id"))
    private Set<Material> materials;

    public Loan(){

    }
}
