package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

@Data
public class MaterialRequestDTO {
    private String name;
    private String type;
    private int amount;
    private boolean available;
}