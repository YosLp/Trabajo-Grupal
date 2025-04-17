package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EvaluationDTO {

    private Long idEvaluation;
    private int type;
    private int maxScore;
    private Date evaluationDate;
    private Long teacherId;
    private Long groupId;
}
