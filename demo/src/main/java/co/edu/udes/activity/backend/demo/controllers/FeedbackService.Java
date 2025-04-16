package co.edu.udes.activity.backend.demo.controller;

import co.edu.udes.activity.backend.demo.models.Feedback;
import co.edu.udes.activity.backend.demo.models.Student;
import co.edu.udes.activity.backend.demo.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{id}")
    public Optional<Feedback> getFeedbackById(@PathVariable int id) {
        return feedbackService.getFeedbackById(id);
    }

    @PostMapping
    public Feedback createFeedback(@RequestBody Feedback feedback) {
        return feedbackService.saveFeedback(feedback);
    }

    @PutMapping("/{id}")
    public Feedback updateFeedback(@PathVariable int id, @RequestBody Feedback updatedFeedback) {
        return feedbackService.updateFeedback(id, updatedFeedback);
    }

    @DeleteMapping("/{id}")
    public String deleteFeedback(@PathVariable int id) {
        boolean deleted = feedbackService.deleteFeedback(id);
        return deleted ? "Retroalimentación eliminada correctamente" : "No se encontró la retroalimentación con ID: " + id;
    }

    @PostMapping("/send")
    public String sendFeedback(@RequestBody Student student, @RequestParam String message) {
        try {
            feedbackService.sendFeedback(student, message);
            return "Retroalimentación enviada exitosamente.";
        } catch (Exception e) {
            return "Error al enviar retroalimentación: " + e.getMessage();
        }
    }
}


    