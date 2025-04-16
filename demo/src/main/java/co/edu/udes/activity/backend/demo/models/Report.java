package co.edu.udes.activity.backend.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "Report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReport;

    @Column(name = "reportType", nullable = false)
    private String reportType;

    @Column(name = "generationDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date generationDate;

    @Column(name = "content", length = 2000, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "idSubject", nullable = false)
    private Subject subject;


    @ManyToOne
    @JoinColumn(name = "idStudent", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "idAcademicRecord", nullable = false)
    private AcademicRecord academicRecord;

    public Report() {

    }
}

