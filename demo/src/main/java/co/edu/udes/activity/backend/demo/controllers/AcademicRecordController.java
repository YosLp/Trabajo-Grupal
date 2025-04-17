package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.AcademicRecordDTO;
import co.edu.udes.activity.backend.demo.models.AcademicRecord;
import co.edu.udes.activity.backend.demo.services.AcademicRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/academic-records")
public class AcademicRecordController {

    @Autowired
    private AcademicRecordService academicRecordService;

    @GetMapping
    public List<AcademicRecordDTO> getAllAcademicRecords() {
        return academicRecordService.getAllAcademicRecords();
    }

    @GetMapping("/{id}")
    public Optional<AcademicRecordDTO> getAcademicRecordById(@PathVariable long id) {
        return academicRecordService.getAcademicRecordById(id);
    }

    @PostMapping
    public AcademicRecord createAcademicRecord(@RequestBody AcademicRecordDTO academicRecordDTO) {
        AcademicRecord academicRecord = new AcademicRecord();
        academicRecord.setAcademicHistory(academicRecordDTO.getAcademicHistory());
        return academicRecordService.saveAcademicRecord(academicRecord);
    }

    @PutMapping("/{id}")
    public AcademicRecord updateAcademicRecord(@PathVariable long id, @RequestBody AcademicRecordDTO updatedAcademicRecordDTO) {
        AcademicRecord updatedAcademicRecord = new AcademicRecord();
        updatedAcademicRecord.setAcademicHistory(updatedAcademicRecordDTO.getAcademicHistory());
        return academicRecordService.updateAcademicRecord(id, updatedAcademicRecord);
    }

    @DeleteMapping("/{id}")
    public String deleteAcademicRecord(@PathVariable long id) {
        boolean deleted = academicRecordService.deleteAcademicRecord(id);
        return deleted ? "Registro académico eliminado correctamente" : "No se encontró el registro académico con ID: " + id;
    }

    @GetMapping("/student/{studentId}/history")
    public List<AcademicRecordDTO> getAcademicHistoryByStudentId(@PathVariable Long studentId) {
        return academicRecordService.getAcademicHistoryByStudentId(studentId);
    }
}
