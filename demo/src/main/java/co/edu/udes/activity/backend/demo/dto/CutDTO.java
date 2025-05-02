package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

@Data
public class CutDTO {
    private long id;
    private Long enrollmentId;
    private String name;
    private double percentage;
    private Double cutValue;
}
