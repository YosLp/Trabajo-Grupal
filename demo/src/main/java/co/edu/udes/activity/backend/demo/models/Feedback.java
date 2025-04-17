package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "Feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFeedback;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "sentAt", nullable = false)
    private Date sentAt;

    @ManyToOne
    @JoinColumn(name = "idTeacher", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "idEvaluation", nullable = false)
    private Evaluation evaluation;

    public Feedback() {

    }
}
