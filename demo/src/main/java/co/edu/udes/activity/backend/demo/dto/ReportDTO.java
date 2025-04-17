package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReportDTO {
    private int idReport;
    private String reportType;
    private Date generationDate;
    private String content;
    private long subjectId;
    private long studentId;
    private long academicRecordId;
}
