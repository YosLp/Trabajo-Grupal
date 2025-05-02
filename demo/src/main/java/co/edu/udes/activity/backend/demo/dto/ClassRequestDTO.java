package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ClassRequestDTO {
    private LocalDate fecha;
    private LocalTime starHour;
    private LocalTime endHour;
    private Long groupId;
    private Long classroomId;
}
