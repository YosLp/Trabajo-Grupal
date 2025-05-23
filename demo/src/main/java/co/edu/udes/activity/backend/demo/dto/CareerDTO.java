package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;


@Data
public class CareerDTO {
    private Long idCareer;
    private String name;
    private String description;
    private int totalcredits;
    private int totalsemesters;
}
