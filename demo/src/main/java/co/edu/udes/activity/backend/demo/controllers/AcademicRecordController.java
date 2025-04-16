<<<<<<< Updated upstream
package co.edu.udes.activity.backend.demo.controller;
=======
package co.edu.udes.activity.backend.demo.controllers;
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes

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
    public List<AcademicRecord> getAllAcademicRecords() {
        return academicRecordService.getAllAcademicRecords();
    }

    @GetMapping("/{id}")
    public Optional<AcademicRecord> getAcademicRecordById(@PathVariable int id) {
        return academicRecordService.getAcademicRecordById(id);
    }

    @PostMapping
    public AcademicRecord createAcademicRecord(@RequestBody AcademicRecord academicRecord) {
        return academicRecordService.saveAcademicRecord(academicRecord);
    }

    @PutMapping("/{id}")
    public AcademicRecord updateAcademicRecord(@PathVariable int id, @RequestBody AcademicRecord updatedAcademicRecord) {
        return academicRecordService.updateAcademicRecord(id, updatedAcademicRecord);
    }

    @DeleteMapping("/{id}")
    public String deleteAcademicRecord(@PathVariable int id) {
        boolean deleted = academicRecordService.deleteAcademicRecord(id);
        return deleted ? "Registro académico eliminado correctamente" : "No se encontró el registro académico con ID: " + id;
    }

    @GetMapping("/student/{studentId}/history")
    public List<AcademicRecord> getAcademicHistoryByStudentId(@PathVariable Long studentId) {
        return academicRecordService.getAcademicHistoryByStudentId(studentId);
    }
}
