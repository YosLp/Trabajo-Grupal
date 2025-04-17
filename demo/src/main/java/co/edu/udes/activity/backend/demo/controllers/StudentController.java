package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.StudentDTO;
import co.edu.udes.activity.backend.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.saveStudent(studentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable long id, @RequestBody StudentDTO updatedStudent) {
        StudentDTO updated = studentService.updateStudent(id, updatedStudent);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id) {
        boolean deleted = studentService.deleteStudent(id);
        return deleted ? ResponseEntity.ok("Estudiante eliminado correctamente")
                : ResponseEntity.notFound().build();
    }
}
