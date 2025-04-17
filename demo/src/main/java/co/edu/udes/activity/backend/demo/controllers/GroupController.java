package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.GroupDTO;
import co.edu.udes.activity.backend.demo.models.Group;
import co.edu.udes.activity.backend.demo.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Long id) {
        Optional<GroupDTO> group = groupService.getGroupById(id);
        return group.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GroupDTO> createGroup(@RequestBody Group group) {
        return ResponseEntity.ok(groupService.saveGroup(group));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDTO> updateGroup(@PathVariable Long id, @RequestBody Group updatedGroup) {
        GroupDTO group = groupService.updateGroup(id, updatedGroup);
        return group != null ? ResponseEntity.ok(group) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long id) {
        boolean deleted = groupService.deleteGroup(id);
        return deleted
                ? ResponseEntity.ok("Grupo eliminado correctamente")
                : ResponseEntity.notFound().build();
    }


    @PutMapping("/{groupId}/assign")
    public ResponseEntity<String> assignCourse(@PathVariable int groupId,
                                               @RequestParam int teacherId,
                                               @RequestParam int courseId) {
        boolean assigned = groupService.assignCourse(groupId, teacherId, courseId);
        return assigned
                ? ResponseEntity.ok("Curso y profesor asignados al grupo correctamente")
                : ResponseEntity.badRequest().body("No se pudo asignar curso o profesor al grupo");
    }

    @PutMapping("/{groupId}/unassign")
    public ResponseEntity<String> unassignCourse(@PathVariable int groupId) {
        boolean unassigned = groupService.unassignCourse(groupId);
        return unassigned
                ? ResponseEntity.ok("Curso y profesor desasignados del grupo correctamente")
                : ResponseEntity.badRequest().body("No se pudo desasignar curso o profesor del grupo");
    }
}
