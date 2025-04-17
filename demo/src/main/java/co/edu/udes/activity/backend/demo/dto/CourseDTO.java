package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class CourseDTO {

     private Long idcourse;
    private String name;
    private String description;
    private Date startdate;
    private Date enddate;
    private String content;
    private String objetives;
    private String competencies;
    private Integer capacity;
    private Integer careerId;
    private Integer subjectId;
    private Integer periodId;

}
