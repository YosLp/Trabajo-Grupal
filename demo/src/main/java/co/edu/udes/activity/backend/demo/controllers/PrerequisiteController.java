package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Prerequisite;
import co.edu.udes.activity.backend.demo.services.PrerequisiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prerequisites")
public class PrerequisiteController {

    @Autowired
    private PrerequisiteService prerequisiteService;

    @GetMapping
    public List<Prerequisite> getAllPrerequisites() {
        return prerequisiteService.getAllPrerequisites();
    }

    @GetMapping("/{id}")
    public Optional<Prerequisite> getPrerequisiteById(@PathVariable Integer id) {
        return prerequisiteService.getPrerequisiteById(id);
    }

    @PostMapping
    public Prerequisite createPrerequisite(@RequestBody Prerequisite prerequisite) {
        return prerequisiteService.savePrerequisite(prerequisite);
    }

    @PutMapping("/{id}")
    public Prerequisite updatePrerequisite(@PathVariable Integer id, @RequestBody Prerequisite updatedPrerequisite) {
        return prerequisiteService.updatePrerequisite(id, updatedPrerequisite);
    }

    @DeleteMapping("/{id}")
    public String deletePrerequisite(@PathVariable Integer id) {
        boolean deleted = prerequisiteService.deletePrerequisite(id);
        return deleted ? "Prerequisite deleted successfully" : "Prerequisite not found with id: " + id;
    }
}