package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

@Data
public class AcademicRecordDTO {
    private int idAcademicRecord;
    private String academicHistory;
    private Long groupId;
    private Long studentId;
}
