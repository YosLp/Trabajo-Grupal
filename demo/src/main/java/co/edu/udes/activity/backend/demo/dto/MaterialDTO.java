package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

@Data
public class MaterialDTO {
    private Long id;
    private String name;
    private String type;
    private int amount;
    private boolean available;
}
