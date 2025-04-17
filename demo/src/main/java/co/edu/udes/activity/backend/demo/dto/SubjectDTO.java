package co.edu.udes.activity.backend.demo.dto;


import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class SubjectDTO {


    private Integer idSubject;
    private String name;
    private String content;
    private String objetives;
    private String competencies;
    private Integer prerequisiteId;
    private List<Long> courseIds;
}
