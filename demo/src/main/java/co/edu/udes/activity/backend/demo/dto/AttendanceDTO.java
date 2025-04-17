package co.edu.udes.activity.backend.demo.dto;
import lombok.Data;
import java.util.Date;
import java.util.List;
@Data
public class AttendanceDTO {

    private Integer idAttendance;
    private Date localdate;
    private Boolean status;
    private Integer scheduleId;
}
