package co.edu.udes.activity.backend.demo.dto;

import co.edu.udes.activity.backend.demo.models.Space;
import co.edu.udes.activity.backend.demo.models.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationDTO {

    private long id;
    private LocalTime starTime;
    private LocalTime endTime;
    private LocalDate date;
    private boolean status;
    private User user;
    private Space space;

}
