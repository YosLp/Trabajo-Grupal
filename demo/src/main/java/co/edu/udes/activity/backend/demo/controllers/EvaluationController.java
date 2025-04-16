<<<<<<< Updated upstream
package co.edu.udes.activity.backend.demo.controller;
=======
package co.edu.udes.activity.backend.demo.controllers;
>>>>>>> Stashed changes

import co.edu.udes.activity.backend.demo.models.Evaluation;
import co.edu.udes.activity.backend.demo.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public void createEvaluation(@RequestParam int type, @RequestParam int maxScore) {
        evaluationService.createEvaluation(type, maxScore);
    }

    @PutMapping("/{id}/schedule")
    public String scheduleEvaluation(@PathVariable int id, @RequestParam Date date) {
        boolean scheduled = evaluationService.scheduleEvaluation(id, date);
        return scheduled ? "Evaluación programada correctamente" : "No se encontró la evaluación con ID: " + id;
    }

    @PutMapping("/{id}/modify")
    public String modifyEvaluation(@PathVariable int id, @RequestParam String details) {
        evaluationService.modifyEvaluation(id, details);
        return "Detalles de la evaluación actualizados";
    }

    @DeleteMapping("/{id}")
    public String deleteEvaluation(@PathVariable int id) {
        boolean deleted = evaluationService.deleteEvaluation(id);
        return deleted ? "Evaluación eliminada correctamente" : "No se encontró la evaluación con ID: " + id;
    }
}
