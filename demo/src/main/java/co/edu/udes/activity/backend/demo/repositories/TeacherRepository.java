package co.edu.udes.activity.backend.demo.repositories;

import co.edu.udes.activity.backend.demo.models.Teacher;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByDocumentNumber(String documentNumber);
    Optional<Teacher> findByInstitutionalCode(String institutionalCode);
}
