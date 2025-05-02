package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class EnrollmentDTO {
    private Long idEnrollment;
    private Long studentId;
    private Long groupId;
    private Date enrollmentDate;
    private String status;
    private Double finalQualification;
    private List<Long> cutIds;
}
