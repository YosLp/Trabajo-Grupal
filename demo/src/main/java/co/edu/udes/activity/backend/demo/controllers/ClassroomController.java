package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Classroom;
import co.edu.udes.activity.backend.demo.services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @GetMapping
    public List<Classroom> getAllClassrooms() {
        return classroomService.getAllClassrooms();
    }

    @GetMapping("/{id}")
    public Optional<Classroom> getClassroomById(@PathVariable Integer id) {
        return classroomService.getClassroomById(id);
    }

    @PostMapping
    public Classroom createClassroom(@RequestBody Classroom classroom) {
        return classroomService.saveClassroom(classroom);
    }

    @PutMapping("/{id}")
    public Classroom updateClassroom(@PathVariable Integer id, @RequestBody Classroom updatedClassroom) {
        return classroomService.updateClassroom(id, updatedClassroom);
    }

    @DeleteMapping("/{id}")
    public String deleteClassroom(@PathVariable Integer id) {
        boolean deleted = classroomService.deleteClassroom(id);
        return deleted ? "Classroom deleted successfully" : "Classroom not found with id: " + id;
    }
}