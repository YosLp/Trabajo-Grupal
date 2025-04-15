<<<<<<< HEAD
package co.edu.udes.activity.backend.demo.controller;
=======
package co.edu.udes.activity.backend.demo.controllers;
>>>>>>> feature-lusbin

import co.edu.udes.activity.backend.demo.models.Student;
import co.edu.udes.activity.backend.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        return studentService.updateStudent(id, updatedStudent);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        boolean deleted = studentService.deleteStudent(id);
        return deleted ? "Estudiante eliminado correctamente" : "No se encontró el estudiante con ID: " + id;
    }
}
