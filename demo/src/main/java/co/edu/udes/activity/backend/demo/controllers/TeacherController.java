<<<<<<< Updated upstream
package co.edu.udes.activity.backend.demo.controller;
=======
package co.edu.udes.activity.backend.demo.controllers;
>>>>>>> Stashed changes

import co.edu.udes.activity.backend.demo.models.Teacher;
import co.edu.udes.activity.backend.demo.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public Optional<Teacher> getTeacherById(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }

    @PostMapping
    public Teacher registerTeacher(@RequestBody Teacher teacher) {
        return teacherService.registerTeacher(teacher);
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable Long id, @RequestBody Teacher updatedTeacher) {
        return teacherService.updateTeacher(id, updatedTeacher);
    }

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        boolean deleted = teacherService.deleteTeacher(id);
        return deleted ? "Profesor eliminado correctamente" : "No se encontró el profesor con ID: " + id;
    }
}
