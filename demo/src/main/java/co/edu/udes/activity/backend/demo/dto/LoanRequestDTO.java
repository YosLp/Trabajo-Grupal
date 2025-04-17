package co.edu.udes.activity.backend.demo.dto;

import co.edu.udes.activity.backend.demo.models.Material;
import co.edu.udes.activity.backend.demo.models.Messaging;
import co.edu.udes.activity.backend.demo.models.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class LoanRequestDTO {

    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
    private Long userId;
    private Set<Long> materialsId;
    private String status;
}
