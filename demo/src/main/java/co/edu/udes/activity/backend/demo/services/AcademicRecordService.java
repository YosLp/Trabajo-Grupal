package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.AcademicRecordDTO;
import co.edu.udes.activity.backend.demo.models.AcademicRecord;
import co.edu.udes.activity.backend.demo.repositories.AcademicRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AcademicRecordService {

    private final AcademicRecordRepository academicRecordRepository;

    @Autowired
    public AcademicRecordService(AcademicRecordRepository academicRecordRepository) {
        this.academicRecordRepository = academicRecordRepository;
    }


    public List<AcademicRecordDTO> getAllAcademicRecords() {
        return academicRecordRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<AcademicRecordDTO> getAcademicRecordById(long id) {
        return academicRecordRepository.findById(id)
                .map(this::convertToDTO);
    }

    public AcademicRecord saveAcademicRecord(AcademicRecord academicRecordDTO) {
        // Convertir el DTO a entidad y guardarlo
        AcademicRecord academicRecord = convertToEntity(academicRecordDTO);
        return academicRecordRepository.save(academicRecord);
    }

    public AcademicRecord updateAcademicRecord(long id, AcademicRecord updatedAcademicRecordDTO) {
        return academicRecordRepository.findById(id).map(academicRecord -> {
            academicRecord.setAcademicHistory(updatedAcademicRecordDTO.getAcademicHistory());
            // Aquí deberías incluir lógica para actualizar las relaciones de Group y Student
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

    public List<AcademicRecordDTO> getAcademicHistoryByStudentId(Long studentId) {
        return academicRecordRepository.findByStudentId(studentId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para convertir una entidad AcademicRecord a AcademicRecordDTO
    private AcademicRecordDTO convertToDTO(AcademicRecord academicRecord) {
        AcademicRecordDTO dto = new AcademicRecordDTO();
        dto.setIdAcademicRecord(academicRecord.getIdAcademicRecord());
        dto.setAcademicHistory(academicRecord.getAcademicHistory());
        dto.setGroupId((long) academicRecord.getGroup().getId());
        dto.setStudentId(academicRecord.getStudent().getId());
        return dto;
    }

    // Método para convertir un AcademicRecordDTO a una entidad AcademicRecord
    private AcademicRecord convertToEntity(AcademicRecord academicRecordDTO) {
        AcademicRecord academicRecord = new AcademicRecord();
        academicRecord.setAcademicHistory(academicRecordDTO.getAcademicHistory());
        // Aquí deberías incluir la lógica para obtener el Group y Student según los IDs
        return academicRecord;
    }
}
