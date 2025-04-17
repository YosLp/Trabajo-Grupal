package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.FeedbackDTO;
import co.edu.udes.activity.backend.demo.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FeedbackDTO> createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return ResponseEntity.ok(feedbackService.saveFeedback(feedbackDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDTO> updateFeedback(@PathVariable Long id, @RequestBody FeedbackDTO feedbackDTO) {
        FeedbackDTO updated = feedbackService.updateFeedback(id, feedbackDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long id) {
        boolean deleted = feedbackService.deleteFeedback(id);
        return deleted ? ResponseEntity.ok("Retroalimentación eliminada correctamente") :
                ResponseEntity.notFound().build();
    }
}
