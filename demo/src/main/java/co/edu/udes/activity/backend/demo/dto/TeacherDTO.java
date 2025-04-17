package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

@Data
public class TeacherDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private String roleName;

    private String specialization;
}
