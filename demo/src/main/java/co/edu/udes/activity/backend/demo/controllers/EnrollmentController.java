package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Enrollment;
import co.edu.udes.activity.backend.demo.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public Optional<Enrollment> getEnrollmentById(@PathVariable int id) {
        return enrollmentService.getEnrollmentById(id);
    }

    @PostMapping
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.saveEnrollment(enrollment);
    }

    @PutMapping("/{id}")
    public Enrollment updateEnrollment(@PathVariable int id, @RequestBody Enrollment updatedEnrollment) {
        return enrollmentService.updateEnrollment(id, updatedEnrollment);
    }

    @DeleteMapping("/{id}")
    public String deleteEnrollment(@PathVariable int id) {
        boolean deleted = enrollmentService.deleteEnrollment(id);
        return deleted ? "Matrícula eliminada correctamente" : "No se encontró la matrícula con ID: " + id;
    }
}
