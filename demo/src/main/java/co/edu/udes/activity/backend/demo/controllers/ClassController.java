package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.ClassDTO;
import co.edu.udes.activity.backend.demo.dto.ClassRequestDTO;
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

    @PostMapping
    public ClassDTO createClass(@RequestBody ClassRequestDTO dto) {
        return classService.createClass(dto);
    }

    @GetMapping
    public List<ClassDTO> getAllClasses() {
        return classService.getAllClasses();
    }

    @GetMapping("/group/{groupId}")
    public List<ClassDTO> getClassesByGroup(@PathVariable Long groupId) {
        return classService.getClassesByGroup(groupId);
    }

    @PutMapping("/{id}")
    public ClassDTO updateClass(@PathVariable Long id, @RequestBody ClassRequestDTO dto) {
        return classService.updateClass(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteClass(@PathVariable Long id) {
        classService.deleteClass(id);
    }
}