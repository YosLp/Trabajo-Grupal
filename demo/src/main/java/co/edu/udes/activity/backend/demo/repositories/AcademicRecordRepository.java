package co.edu.udes.activity.backend.demo.repositories;

import co.edu.udes.activity.backend.demo.models.AcademicRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicRecordRepository extends JpaRepository<AcademicRecord, Long> {
    List<AcademicRecord> findByStudentId(Long studentId);
}




