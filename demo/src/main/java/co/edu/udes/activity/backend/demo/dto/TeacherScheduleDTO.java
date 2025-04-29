package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

@Data
public class TeacherScheduleDTO {
    private String groupName;
    private String className;
    private String startHour;
    private String endHour;
}
