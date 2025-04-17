package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

@Data
public class GroupDTO {
    private int id;
    private String name;
    private int studentsAmount;
    private String teacherName;
    private String courseName;
}
