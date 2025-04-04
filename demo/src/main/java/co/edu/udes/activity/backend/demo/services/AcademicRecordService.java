package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.AcademicRecord;
import co.edu.udes.activity.backend.demo.repository.AcademicRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicRecordService {

    @Autowired
    private AcademicRecordRepository academicRecordRepository;

    public List<AcademicRecord> getAllAcademicRecords() {
        return academicRecordRepository.findAll();
    }

    public Optional<AcademicRecord> getAcademicRecordById(int id) {
        return academicRecordRepository.findById(id);
    }

    public AcademicRecord saveAcademicRecord(AcademicRecord academicRecord) {
        return academicRecordRepository.save(academicRecord);
    }

    public AcademicRecord updateAcademicRecord(int id, AcademicRecord updatedAcademicRecord) {
        return academicRecordRepository.findById(id).map(academicRecord -> {
            academicRecord.setAcademicHistory(updatedAcademicRecord.getAcademicHistory());
            academicRecord.setGroup(updatedAcademicRecord.getGroup());
            academicRecord.setStudent(updatedAcademicRecord.getStudent());
            return academicRecordRepository.save(academicRecord);
        }).orElse(null);
    }

    public boolean deleteAcademicRecord(int id) {
        if (academicRecordRepository.existsById(id)) {
            academicRecordRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
