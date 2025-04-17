package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.Date;

@Entity
@Data
@Table(name = "Evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private int type;

    @Column(name = "maxScore", nullable = false)
    private int maxScore;

    @Column(name = "evaluationDate", nullable = false)
    private Date evaluationDate;

    @ManyToOne
    @JoinColumn(name = "idTeacher", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "idGroup", nullable = false)
    private Group group;

    @OneToMany(mappedBy = "evaluation")
    private List<Feedback> feedbacks;

    public Evaluation() {
    }
}
