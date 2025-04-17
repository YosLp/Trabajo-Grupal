package co.edu.udes.activity.backend.demo.dto;


import co.edu.udes.activity.backend.demo.models.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessagingDTO {

    private long id;
    private String content;
    private LocalDateTime sendDate;
    private boolean read;
    private String messageType;
    private User sender;
    private User receiver;
}


