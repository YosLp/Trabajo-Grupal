package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.TeacherDTO;
import co.edu.udes.activity.backend.demo.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long id) {
        return teacherService.getTeacherById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacherDTO) {
        return ResponseEntity.ok(teacherService.saveTeacher(teacherDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO updatedDTO) {
        TeacherDTO updated = teacherService.updateTeacher(id, updatedDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
        boolean deleted = teacherService.deleteTeacher(id);
        return deleted
                ? ResponseEntity.ok("Profesor eliminado correctamente")
                : ResponseEntity.notFound().build();
    }
}
