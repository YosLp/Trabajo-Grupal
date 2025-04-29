package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

@Data
public class StudentScheduleDTO {
    private String courseName;
    private String className;
    private String startHour;
    private String endHour;
}
