package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Evaluation;
import co.edu.udes.activity.backend.demo.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import co.edu.udes.activity.backend.demo.repositories.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        evaluationRepository.save(evaluation);
    }

    public void recordScores() {
    }

    public boolean scheduleEvaluation(int id, Date date) {
        Optional<Evaluation> evaluation = evaluationRepository.findById(id);
        if (evaluation.isPresent()) {
            Evaluation eval = evaluation.get();
            eval.setEvaluationDate(date);
            evaluationRepository.save(eval);
            return true;
        }
        return false;
    }

    public void modifyEvaluation(int id, String details) {
        Optional<Evaluation> evaluation = evaluationRepository.findById(id);
        if (evaluation.isPresent()) {
            Evaluation eval = evaluation.get();
            evaluationRepository.save(eval);
        }
    }


    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    public Optional<Evaluation> getEvaluationById(int id) {
    public Optional<Evaluation> getEvaluationById(long id) {
        return evaluationRepository.findById(id);
    }

    public Evaluation saveEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    public Evaluation updateEvaluation(int id, Evaluation updatedEvaluation) {
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

    public boolean deleteEvaluation(int id) {
    public boolean deleteEvaluation(long id) {
        if (evaluationRepository.existsById(id)) {
            evaluationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
