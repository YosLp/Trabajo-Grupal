package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class GroupDTO {
    private long id;
    private String name;
    private int studentsAmount;
    private Long teacherId;
    private Long subjectId;
    private List<Long> enrollmentIds;
    private List<Integer> classIds;
}
