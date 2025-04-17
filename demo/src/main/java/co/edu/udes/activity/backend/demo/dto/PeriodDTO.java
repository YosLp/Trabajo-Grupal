package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class PeriodDTO {

    private Integer idPeriod;
    private String name;
    private Date stardate;
    private Date enddate;
    private List<Long> courseIds;

}
