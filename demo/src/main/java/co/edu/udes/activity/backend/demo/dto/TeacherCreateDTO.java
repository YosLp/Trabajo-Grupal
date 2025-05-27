package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

@Data
public class TeacherCreateDTO {
    private Long userId;
    private String specialization;
    private String institutionalCode;
    private Boolean statusContract;
}
