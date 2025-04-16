package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

@Data
public class SpaceRequestDTO {
    private String name;
    private String type;
    private int capacity;
    private boolean available;
}
