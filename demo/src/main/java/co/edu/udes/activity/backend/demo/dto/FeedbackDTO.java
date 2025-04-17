package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;
import java.util.Date;

@Data
public class FeedbackDTO {
    private int idFeedback;
    private String message;
    private Date sentAt;
    private long teacherId;
    private long evaluationId;
}
