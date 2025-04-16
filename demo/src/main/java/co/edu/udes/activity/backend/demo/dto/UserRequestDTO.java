package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

//EPA ESTE ES PARA EL FRONTEND OSEA LO Q EL FRONTEND DA
@Data
public class UserRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean status;
    private Long roleId;
}
