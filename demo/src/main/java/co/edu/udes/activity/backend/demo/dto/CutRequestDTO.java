package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

@Data
public class CutRequestDTO {
    private String name;
    private double percentage;
    private Double cutValue;
    private Long enrollmentId;
}