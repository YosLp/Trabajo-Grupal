package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EnrollmentRequestDTO {
    private Long studentId;
    private Long groupId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date enrollmentDate;

    private String status;
    private Long qualification;
    private Long p1Qualification;
    private Long p2Qualification;
    private Long p3Qualification;
}
