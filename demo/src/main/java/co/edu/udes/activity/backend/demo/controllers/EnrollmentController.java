package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.EnrollmentDTO;
import co.edu.udes.activity.backend.demo.dto.EnrollmentRequestDTO;
import co.edu.udes.activity.backend.demo.dto.NotasRequestDTO;
import co.edu.udes.activity.backend.demo.models.Enrollment;
import co.edu.udes.activity.backend.demo.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAll() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> getById(@PathVariable Long id) {
        EnrollmentDTO dto = enrollmentService.getEnrollmentById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> create(@RequestBody EnrollmentRequestDTO req) {
        EnrollmentDTO dto = enrollmentService.createEnrollment(req);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> update(
            @PathVariable Long id,
            @RequestBody EnrollmentRequestDTO req) {
        EnrollmentDTO dto = enrollmentService.updateEnrollment(id, req);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = enrollmentService.deleteEnrollment(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByStudent(@PathVariable Long studentId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);
        List<EnrollmentDTO> dtos = enrollments.stream()
                .map(enrollment -> enrollmentService.mapToDTO(enrollment))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/student/{studentId}/group/{groupId}/grades")
    public ResponseEntity<EnrollmentDTO> getGradesByStudentAndGroup(
            @PathVariable Long studentId,
            @PathVariable Long groupId) {

        EnrollmentDTO dto = enrollmentService.getEnrollmentGradesByStudentAndGroup(studentId, groupId);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/cargar-notas")
    public ResponseEntity<EnrollmentDTO> cargarNotas(
            @PathVariable Long id,
            @RequestBody NotasRequestDTO dto) {
        EnrollmentDTO updated = enrollmentService.cargarNotas(id, dto);
        return ResponseEntity.ok(updated);
    }
}
