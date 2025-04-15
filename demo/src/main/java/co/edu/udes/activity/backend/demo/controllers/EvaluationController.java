<<<<<<< HEAD
package co.edu.udes.activity.backend.demo.controller;
=======
package co.edu.udes.activity.backend.demo.controllers;
>>>>>>> feature-lusbin

import co.edu.udes.activity.backend.demo.models.Evaluation;
import co.edu.udes.activity.backend.demo.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @GetMapping
    public List<Evaluation> getAllEvaluations() {
        return evaluationService.getAllEvaluations();
    }

    @GetMapping("/{id}")
    public Optional<Evaluation> getEvaluationById(@PathVariable int id) {
        return evaluationService.getEvaluationById(id);
    }

    @PostMapping
    public Evaluation createEvaluation(@RequestBody Evaluation evaluation) {
        return evaluationService.saveEvaluation(evaluation);
    }

    @PutMapping("/{id}")
    public Evaluation updateEvaluation(@PathVariable int id, @RequestBody Evaluation updatedEvaluation) {
        return evaluationService.updateEvaluation(id, updatedEvaluation);
    }

    @DeleteMapping("/{id}")
    public String deleteEvaluation(@PathVariable int id) {
        boolean deleted = evaluationService.deleteEvaluation(id);
        return deleted ? "Evaluación eliminada correctamente" : "No se encontró la evaluación con ID: " + id;
    }
}
