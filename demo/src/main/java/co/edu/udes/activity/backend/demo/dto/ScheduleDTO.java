package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class ScheduleDTO {

    private Long idSchedule;
    private Date starHour;
    private Date endHour;
    private List<Long> classIds;

}
