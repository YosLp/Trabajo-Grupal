package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

@Data
public class EnrollmentDTO {
    private Long id;
    private String studentName;
    private String groupName;
    private String enrollmentDate;
    private String status;
}
