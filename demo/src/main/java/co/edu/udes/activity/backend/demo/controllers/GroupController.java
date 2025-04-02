package co.edu.udes.activity.backend.demo.controller;

import co.edu.udes.activity.backend.demo.models.Group;
import co.edu.udes.activity.backend.demo.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/{id}")
    public Optional<Group> getGroupById(@PathVariable int id) {
        return groupService.getGroupById(id);
    }

    @PostMapping
    public Group createGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }

    @PutMapping("/{id}")
    public Group updateGroup(@PathVariable int id, @RequestBody Group updatedGroup) {
        return groupService.updateGroup(id, updatedGroup);
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable int id) {
        boolean deleted = groupService.deleteGroup(id);
        return deleted ? "Grupo eliminado correctamente" : "No se encontró el grupo con ID: " + id;
    }
}
