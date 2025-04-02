package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Feedback;
import co.edu.udes.activity.backend.demo.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Optional<Feedback> getFeedbackById(int id) {
        return feedbackRepository.findById(id);
    }

    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Feedback updateFeedback(int id, Feedback updatedFeedback) {
        return feedbackRepository.findById(id).map(feedback -> {
            feedback.setMessage(updatedFeedback.getMessage());
            feedback.setSentAt(updatedFeedback.getSentAt());
            feedback.setTeacher(updatedFeedback.getTeacher());
            feedback.setEvaluation(updatedFeedback.getEvaluation());
            return feedbackRepository.save(feedback);
        }).orElse(null);
    }

    public boolean deleteFeedback(int id) {
        if (feedbackRepository.existsById(id)) {
            feedbackRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
