package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;
import java.util.Date;

@Data
public class EnrollmentDTO {
    private Long idEnrollment;
    private Long studentId;
    private Long groupId;
    private Date enrollmentDate;
    private String status;
    private Double qualification;
    private Double p1Qualification;
    private Double p2Qualification;
    private Double p3Qualification;
    private String studentName;
    private String subjectName;
}
