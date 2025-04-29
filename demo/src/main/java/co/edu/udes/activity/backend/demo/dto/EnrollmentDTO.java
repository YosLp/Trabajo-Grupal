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
    private Long qualification;
    private Long p1Qualification;
    private Long p2Qualification;
    private Long p3Qualification;
}
