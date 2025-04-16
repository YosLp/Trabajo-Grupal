package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Class;
import co.edu.udes.activity.backend.demo.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping
    public List<Class> getAllClasses() {
        return classService.getAllClasses();
    }

    @GetMapping("/{id}")
    public Optional<Class> getClassById(@PathVariable Integer id) {
        return classService.getClassById(id);
    }

    @PostMapping
    public Class createClass(@RequestBody Class clazz) {
        return classService.saveClass(clazz);
    }

    @PutMapping("/{id}")
    public Class updateClass(@PathVariable Integer id, @RequestBody Class updatedClass) {
        return classService.updateClass(id, updatedClass);
    }

    @DeleteMapping("/{id}")
    public String deleteClass(@PathVariable Integer id) {
        boolean deleted = classService.deleteClass(id);
        return deleted ? "Class deleted successfully" : "Class not found with id: " + id;
    }
}