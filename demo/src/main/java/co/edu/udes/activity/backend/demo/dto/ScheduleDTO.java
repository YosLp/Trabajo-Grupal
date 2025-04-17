package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class ScheduleDTO {

    private Integer idSchedule;
    private Date starHour;
    private Date endHour;
    private List<Integer> classIds;

}
