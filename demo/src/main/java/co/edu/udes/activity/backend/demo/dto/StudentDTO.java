package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private String roleName;

    private String address;
    private String statusStudent;
    private String registrationDate;
    private String phoneNumber;
}
