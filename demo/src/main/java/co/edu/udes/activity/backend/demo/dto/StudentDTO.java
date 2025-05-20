package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String Status;
    private String address;
    private String statusStudent;
    private String registrationDate;
    private String phoneNumber;
    private Integer careerId;
    private String roleName;
    private List<Long> attendanceIds;
}

