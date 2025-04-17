package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class ClassroomDTO {
    private Integer idClassroom;
    private String location;
    private String capacity;
    private List<Integer> classIds;
}
