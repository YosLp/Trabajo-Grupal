package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.EnrollmentDTO;
import co.edu.udes.activity.backend.demo.models.Enrollment;
import co.edu.udes.activity.backend.demo.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> createEnrollment(@RequestBody Enrollment enrollment) {
        return ResponseEntity.ok(enrollmentService.saveEnrollment(enrollment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment updatedEnrollment) {
        EnrollmentDTO updated = enrollmentService.updateEnrollment(id, updatedEnrollment);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEnrollment(@PathVariable Long id) {
        boolean deleted = enrollmentService.deleteEnrollment(id);
        return deleted
                ? ResponseEntity.ok("Matrícula eliminada correctamente")
                : ResponseEntity.notFound().build();
    }
}
