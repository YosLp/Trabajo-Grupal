package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Subject;
import co.edu.udes.activity.backend.demo.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public Optional<Subject> getSubjectById(@PathVariable Integer id) {
        return subjectService.getSubjectById(id);
    }

    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectService.saveSubject(subject);
    }

    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable Integer id, @RequestBody Subject updatedSubject) {
        return subjectService.updateSubject(id, updatedSubject);
    }

    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Integer id) {
        boolean deleted = subjectService.deleteSubject(id);
        return deleted ? "Subject deleted successfully" : "Subject not found with id: " + id;
    }
}