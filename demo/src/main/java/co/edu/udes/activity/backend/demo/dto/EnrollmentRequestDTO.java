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
    private long qualification;
    private long p1Qualification;
    private long p2Qualification;
    private long p3Qualification;
}