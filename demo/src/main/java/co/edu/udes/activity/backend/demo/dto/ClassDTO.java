package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


@Data
public class ClassDTO {
    private Long id;
    private LocalDate fecha;
    private LocalTime starHour;
    private LocalTime endHour;
    private Long groupId;
    private Long classroomId;
}
