package co.edu.udes.activity.backend.demo.services;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
import co.edu.udes.activity.backend.demo.models.Feedback;
import co.edu.udes.activity.backend.demo.repository.FeedbackRepository;
=======
=======
>>>>>>> Stashed changes
import co.edu.udes.activity.backend.demo.models.*;
import co.edu.udes.activity.backend.demo.repositories.FeedbackRepository;
import co.edu.udes.activity.backend.demo.repositories.AcademicRecordRepository;
import co.edu.udes.activity.backend.demo.repositories.EvaluationRepository;
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private AcademicRecordRepository academicRecordRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;  // Añadido para obtener evaluaciones

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

    public void sendFeedback(Student student, String message) {
        List<AcademicRecord> records = academicRecordRepository.findByStudentId(student.getId());

        if (!records.isEmpty()) {
            AcademicRecord record = records.get(0);

            Evaluation evaluation = getLatestEvaluationForGroup(record.getGroup());

            if (evaluation != null) {
                Teacher teacher = evaluation.getTeacher();

                Feedback feedback = new Feedback();
                feedback.setMessage(message);
                feedback.setSentAt(new Date());
                feedback.setEvaluation(evaluation);
                feedback.setTeacher(teacher);

                feedbackRepository.save(feedback);
            } else {
                throw new RuntimeException("No se encontró una evaluación para el grupo de este registro académico.");
            }
        } else {
            throw new RuntimeException("No se encontró AcademicRecord para el estudiante con ID: " + student.getId());
        }
    }

    private Evaluation getLatestEvaluationForGroup(Group group) {
        List<Evaluation> evaluations = evaluationRepository.findByGroupId(group.getIdGroup());
        return evaluations.isEmpty() ? null : evaluations.get(0);  // Ajustar si es necesario ordenar por fecha
    }
}
