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

<<<<<<< Updated upstream
    public Optional<AcademicRecord> getAcademicRecordById(int id) {
=======
    public Optional<AcademicRecord> getAcademicRecordById(long id) {
>>>>>>> Stashed changes
        return academicRecordRepository.findById(id);
    }

    public AcademicRecord saveAcademicRecord(AcademicRecord academicRecord) {
        return academicRecordRepository.save(academicRecord);
    }

<<<<<<< Updated upstream
    public AcademicRecord updateAcademicRecord(int id, AcademicRecord updatedAcademicRecord) {
=======
    public AcademicRecord updateAcademicRecord(long id, AcademicRecord updatedAcademicRecord) {
>>>>>>> Stashed changes
        return academicRecordRepository.findById(id).map(academicRecord -> {
            academicRecord.setAcademicHistory(updatedAcademicRecord.getAcademicHistory());
            academicRecord.setGroup(updatedAcademicRecord.getGroup());
            academicRecord.setStudent(updatedAcademicRecord.getStudent());
            return academicRecordRepository.save(academicRecord);
        }).orElse(null);
    }

<<<<<<< Updated upstream
    public boolean deleteAcademicRecord(int id) {
=======
    public boolean deleteAcademicRecord(long id) {
>>>>>>> Stashed changes
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

