package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;


@Data
public class ClassDTO {

    private Integer idClass;
    private Date starHour;
    private Date endHour;
    private String description;
    private Integer groupId;
    private Integer scheduleId;
    private Integer classroomId;
}
