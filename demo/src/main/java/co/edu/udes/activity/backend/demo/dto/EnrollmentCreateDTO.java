package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EnrollmentCreateDTO {
    private Long studentId;
    private int groupId;
    private Date enrollmentDate;
    private String status;
}

