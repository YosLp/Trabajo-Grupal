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
    private Double qualification;
    private Double p1Qualification;
    private Double p2Qualification;
    private Double p3Qualification;
}
