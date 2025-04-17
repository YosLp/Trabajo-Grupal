package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class PrerequisiteDTO {

    private Integer idPrerequisite;
    private String subject;
    private String description;
    private List<Integer> subjectIds;

}
