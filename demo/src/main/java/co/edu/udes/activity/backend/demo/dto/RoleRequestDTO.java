package co.edu.udes.activity.backend.demo.dto;


import lombok.Data;

import java.util.Set;

@Data
public class RoleRequestDTO {

    private String name;
    private String description;
    private Set<Long> permissionsId;
}
