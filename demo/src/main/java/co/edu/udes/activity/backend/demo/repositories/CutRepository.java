package co.edu.udes.activity.backend.demo.repositories;

import co.edu.udes.activity.backend.demo.models.AcademicRecord;
import co.edu.udes.activity.backend.demo.models.Cut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CutRepository extends JpaRepository<Cut, Long> {
    List<Cut> findByEnrollmentId(Long enrollmentId);
}

