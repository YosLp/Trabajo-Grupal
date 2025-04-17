package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;


@Data
public class CareerDTO {

    private Integer idCareer;
    private String name;
    private String description;
    private List<Long> courseIds;
}
