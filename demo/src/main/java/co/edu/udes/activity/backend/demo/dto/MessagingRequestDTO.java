package co.edu.udes.activity.backend.demo.dto;

import co.edu.udes.activity.backend.demo.models.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessagingRequestDTO {

    private String content;
    private LocalDateTime sendDate;
    private String messageType;
    private Long senderId;
    private Long receiverId;
}
