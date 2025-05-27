package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

@Data
public class UserWPasswordDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean status;
    private Long roleId;
    private String documentNumber;
}
