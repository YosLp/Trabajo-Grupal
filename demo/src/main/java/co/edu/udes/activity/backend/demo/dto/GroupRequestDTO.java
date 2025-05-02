package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

@Data
public class GroupRequestDTO {
    private String name;
    private int studentsAmount;
    private Long subjectId;
    private Long teacherId;
}
