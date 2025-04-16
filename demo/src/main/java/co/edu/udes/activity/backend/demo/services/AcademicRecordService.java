package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.AcademicRecord;
import co.edu.udes.activity.backend.demo.repositories.AcademicRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicRecordService {

    private final AcademicRecordRepository academicRecordRepository;

    @Autowired
    public AcademicRecordService(AcademicRecordRepository academicRecordRepository) {
        this.academicRecordRepository = academicRecordRepository;
    }

    public List<AcademicRecord> getAllAcademicRecords() {
        return academicRecordRepository.findAll();
    }

    public Optional<AcademicRecord> getAcademicRecordById(long id) {
        return academicRecordRepository.findById(id);
    }

    public AcademicRecord saveAcademicRecord(AcademicRecord academicRecord) {
        return academicRecordRepository.save(academicRecord);
    }

    public AcademicRecord updateAcademicRecord(long id, AcademicRecord updatedAcademicRecord) {
        return academicRecordRepository.findById(id).map(academicRecord -> {
            academicRecord.setAcademicHistory(updatedAcademicRecord.getAcademicHistory());
            academicRecord.setGroup(updatedAcademicRecord.getGroup());
            academicRecord.setStudent(updatedAcademicRecord.getStudent());
            return academicRecordRepository.save(academicRecord);
        }).orElse(null);
    }

    public boolean deleteAcademicRecord(long id) {
        if (academicRecordRepository.existsById(id)) {
            academicRecordRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<AcademicRecord> getAcademicHistoryByStudentId(Long studentId) {
        return academicRecordRepository.findByStudentId(studentId);
    }
}
