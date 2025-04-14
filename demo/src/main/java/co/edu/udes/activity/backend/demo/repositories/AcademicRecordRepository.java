package co.edu.udes.activity.backend.demo.repositories;

import co.edu.udes.activity.backend.demo.models.AcademicRecord;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AcademicRecordRepository extends JpaRepository<AcademicRecord, Long> {
}

