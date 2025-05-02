package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {
    private Long id;
    private String address;
    private String statusStudent;
    private String registrationDate;
    private String phoneNumber;
    private Integer careerId;
    private List<Long> attendanceIds;
}
