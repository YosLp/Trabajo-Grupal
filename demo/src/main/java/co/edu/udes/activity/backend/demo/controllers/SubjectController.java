package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.SubjectDTO;
import co.edu.udes.activity.backend.demo.dto.SubjectRequestDTO;
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

    @PostMapping
    public SubjectDTO createSubject(@RequestBody SubjectRequestDTO dto) {
        return subjectService.createSubject(dto);
    }

    @GetMapping
    public List<SubjectDTO> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @PostMapping("/{subjectId}/prerequisite/{prerequisiteId}")
    public SubjectDTO assignPrerequisite(
            @PathVariable Long subjectId,
            @PathVariable Long prerequisiteId) {
        return subjectService.assignPrerequisite(subjectId, prerequisiteId);
    }

    @GetMapping("/{subjectId}/prerequisites")
    public List<SubjectDTO> getPrerequisites(@PathVariable Long subjectId) {
        return subjectService.getPrerequisites(subjectId);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
    }
}