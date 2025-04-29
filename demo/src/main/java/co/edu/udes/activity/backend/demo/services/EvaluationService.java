package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.EvaluationDTO;
import co.edu.udes.activity.backend.demo.models.Evaluation;
import co.edu.udes.activity.backend.demo.models.Group;
import co.edu.udes.activity.backend.demo.models.Teacher;
import co.edu.udes.activity.backend.demo.repositories.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    public Evaluation saveEvaluation(EvaluationDTO evaluationDTO) {
        Teacher teacher = new Teacher();
        teacher.setId(evaluationDTO.getTeacherId());

        Group group = new Group();
        group.setId(Math.toIntExact(evaluationDTO.getGroupId()));

        Evaluation evaluation = new Evaluation();
        evaluation.setType(evaluationDTO.getType());
        evaluation.setMaxScore(evaluationDTO.getMaxScore());
        evaluation.setEvaluationDate(new Date());
        evaluation.setTeacher(teacher);
        evaluation.setGroup(group);
        return evaluationRepository.save(evaluation);
    }

    public List<EvaluationDTO> getAllEvaluations() {
        return evaluationRepository.findAll().stream()
                .map(evaluation -> {
                    EvaluationDTO dto = new EvaluationDTO();
                    dto.setIdEvaluation(evaluation.getId());
                    dto.setType(evaluation.getType());
                    dto.setMaxScore(evaluation.getMaxScore());
                    dto.setEvaluationDate(evaluation.getEvaluationDate());

                    if (evaluation.getTeacher() != null) {
                        dto.setTeacherId(evaluation.getTeacher().getId());
                    }

                    if (evaluation.getGroup() != null) {
                        dto.setGroupId((long) evaluation.getGroup().getId());
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }

    public EvaluationDTO getEvaluationById(Long id) {
        Optional<Evaluation> evaluation = evaluationRepository.findById(id);
        if (evaluation.isPresent()) {
            Evaluation eval = evaluation.get();
            EvaluationDTO dto = new EvaluationDTO();
            dto.setIdEvaluation(eval.getId());
            dto.setType(eval.getType());
            dto.setMaxScore(eval.getMaxScore());
            dto.setEvaluationDate(eval.getEvaluationDate());

            if (eval.getTeacher() != null) {
                dto.setTeacherId(eval.getTeacher().getId());
            }

            if (eval.getGroup() != null) {
                dto.setGroupId((long) eval.getGroup().getId());
            }

            return dto;
        }
        return null;
    }

    public Evaluation updateEvaluation(Long id, EvaluationDTO updatedEvaluationDTO) {
        Optional<Evaluation> evaluation = evaluationRepository.findById(id);
        if (evaluation.isPresent()) {
            Evaluation eval = evaluation.get();
            eval.setType(updatedEvaluationDTO.getType());
            eval.setMaxScore(updatedEvaluationDTO.getMaxScore());
            eval.setEvaluationDate(updatedEvaluationDTO.getEvaluationDate());

            Teacher teacher = new Teacher();
            teacher.setId(updatedEvaluationDTO.getTeacherId());
            eval.setTeacher(teacher);

            Group group = new Group();
            group.setId(Math.toIntExact(updatedEvaluationDTO.getGroupId()));
            eval.setGroup(group);

            return evaluationRepository.save(eval);
        }
        return null;
    }

    public boolean deleteEvaluation(Long id) {
        if (evaluationRepository.existsById(id)) {
            evaluationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
