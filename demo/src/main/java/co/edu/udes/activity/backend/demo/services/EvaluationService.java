package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Evaluation;
import co.edu.udes.activity.backend.demo.repositories.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    public void createEvaluation(int type, int maxScore) {
        Evaluation evaluation = new Evaluation();
        evaluation.setType(type);
        evaluation.setMaxScore(maxScore);
        evaluation.setEvaluationDate(new Date());
        evaluationRepository.save(evaluation);
    }

    public boolean scheduleEvaluation(Long id, Date date) {
        Optional<Evaluation> evaluation = evaluationRepository.findById(id);
        if (evaluation.isPresent()) {
            Evaluation eval = evaluation.get();
            eval.setEvaluationDate(date);
            evaluationRepository.save(eval);
            return true;
        }
        return false;
    }

    public void modifyEvaluation(Long id, String details) {
        Optional<Evaluation> evaluation = evaluationRepository.findById(id);
        if (evaluation.isPresent()) {
            Evaluation eval = evaluation.get();
            evaluationRepository.save(eval);
        }
    }

    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    public Optional<Evaluation> getEvaluationById(Long id) {
        return evaluationRepository.findById(id);
    }

    public Evaluation saveEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    public Evaluation updateEvaluation(Long id, Evaluation updatedEvaluation) {
        return evaluationRepository.findById(id).map(evaluation -> {
            evaluation.setType(updatedEvaluation.getType());
            evaluation.setMaxScore(updatedEvaluation.getMaxScore());
            evaluation.setEvaluationDate(updatedEvaluation.getEvaluationDate());
            evaluation.setTeacher(updatedEvaluation.getTeacher());
            evaluation.setGroup(updatedEvaluation.getGroup());
            return evaluationRepository.save(evaluation);
        }).orElse(null);
    }

    public boolean deleteEvaluation(Long id) {
        if (evaluationRepository.existsById(id)) {
            evaluationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
