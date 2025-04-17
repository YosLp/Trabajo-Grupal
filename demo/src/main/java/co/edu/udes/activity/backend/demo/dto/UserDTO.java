package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;


@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean status;
    private String roleName;
}
