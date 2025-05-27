package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

@Data
public class TeacherDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean status;
    private String roleName;
    private String specialization;
    private String password;
    private String documentNumber;
    private String institutionalCode;
    private Boolean statusContract;
}

