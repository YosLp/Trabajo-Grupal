package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.EvaluationDTO;
import co.edu.udes.activity.backend.demo.models.Evaluation;
import co.edu.udes.activity.backend.demo.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @GetMapping
    public List<EvaluationDTO> getAllEvaluations() {
        return evaluationService.getAllEvaluations();
    }

    @GetMapping("/{id}")
    public EvaluationDTO getEvaluationById(@PathVariable Long id) {
        return evaluationService.getEvaluationById(id);
    }

    @PostMapping
    public Evaluation createEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        return evaluationService.saveEvaluation(evaluationDTO);
    }

    @PutMapping("/{id}")
    public Evaluation updateEvaluation(@PathVariable Long id, @RequestBody EvaluationDTO updatedEvaluationDTO) {
        return evaluationService.updateEvaluation(id, updatedEvaluationDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteEvaluation(@PathVariable Long id) {
        boolean deleted = evaluationService.deleteEvaluation(id);
        return deleted ? "Evaluación eliminada correctamente" : "No se encontró la evaluación con ID: " + id;
    }
}
