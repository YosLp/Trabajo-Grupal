package co.edu.udes.activity.backend.demo.repositories;

import co.edu.udes.activity.backend.demo.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    // Método para encontrar todos los Feedbacks por evaluación
    List<Feedback> findByEvaluationId(int evaluationId);

    // Método para encontrar todos los Feedbacks por profesor
    List<Feedback> findByTeacherId(long teacherId);

    // Método para encontrar todos los Feedbacks por grupo
    List<Feedback> findByEvaluationGroupId(long groupId);

    // Método para encontrar todos los Feedbacks de un profesor en una evaluación específica
    List<Feedback> findByTeacherIdAndEvaluationId(long teacherId, int evaluationId);
}
