package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.FeedbackDTO;
import co.edu.udes.activity.backend.demo.models.*;
import co.edu.udes.activity.backend.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private AcademicRecordRepository academicRecordRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<FeedbackDTO> getAllFeedbacks() {
        return feedbackRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<FeedbackDTO> getFeedbackById(Long id) {
        return feedbackRepository.findById(id).map(this::convertToDTO);
    }

    public FeedbackDTO saveFeedback(FeedbackDTO dto) {
        Feedback feedback = convertToEntity(dto);
        feedback.setSentAt(new Date());
        return convertToDTO(feedbackRepository.save(feedback));
    }

    public FeedbackDTO updateFeedback(Long id, FeedbackDTO dto) {
        return feedbackRepository.findById(id).map(feedback -> {
            feedback.setMessage(dto.getMessage());
            feedback.setSentAt(dto.getSentAt());

            teacherRepository.findById(dto.getTeacherId()).ifPresent(feedback::setTeacher);
            evaluationRepository.findById(dto.getEvaluationId()).ifPresent(feedback::setEvaluation);

            return convertToDTO(feedbackRepository.save(feedback));
        }).orElse(null);
    }

    public boolean deleteFeedback(Long id) {
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
        List<Evaluation> evaluations = evaluationRepository.findByGroupId((long) group.getId());
        return evaluations.isEmpty() ? null : evaluations.get(0);
    }

    private FeedbackDTO convertToDTO(Feedback feedback) {
        FeedbackDTO dto = new FeedbackDTO();
        dto.setIdFeedback(Math.toIntExact(feedback.getIdFeedback()));
        dto.setMessage(feedback.getMessage());
        dto.setSentAt(feedback.getSentAt());
        dto.setTeacherId(feedback.getTeacher().getId());
        dto.setEvaluationId(feedback.getEvaluation().getId());
        return dto;
    }

    private Feedback convertToEntity(FeedbackDTO dto) {
        Feedback feedback = new Feedback();
        feedback.setIdFeedback((long) dto.getIdFeedback());
        feedback.setMessage(dto.getMessage());
        feedback.setSentAt(dto.getSentAt());

        teacherRepository.findById(dto.getTeacherId()).ifPresent(feedback::setTeacher);
        evaluationRepository.findById(dto.getEvaluationId()).ifPresent(feedback::setEvaluation);

        return feedback;
    }
}
