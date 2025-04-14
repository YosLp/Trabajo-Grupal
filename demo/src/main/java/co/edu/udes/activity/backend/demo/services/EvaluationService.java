package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Evaluation;
import co.edu.udes.activity.backend.demo.repositories.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    public Optional<Evaluation> getEvaluationById(long id) {
        return evaluationRepository.findById(id);
    }

    public Evaluation saveEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    public Evaluation updateEvaluation(long id, Evaluation updatedEvaluation) {
        return evaluationRepository.findById(id).map(evaluation -> {
            evaluation.setType(updatedEvaluation.getType());
            evaluation.setMaxScore(updatedEvaluation.getMaxScore());
            evaluation.setEvaluationDate(updatedEvaluation.getEvaluationDate());
            evaluation.setTeacher(updatedEvaluation.getTeacher());
            evaluation.setGroup(updatedEvaluation.getGroup());
            return evaluationRepository.save(evaluation);
        }).orElse(null);
    }

    public boolean deleteEvaluation(long id) {
        if (evaluationRepository.existsById(id)) {
            evaluationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
