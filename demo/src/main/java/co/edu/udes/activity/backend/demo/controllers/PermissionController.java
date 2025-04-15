package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Permission;
import co.edu.udes.activity.backend.demo.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {
    
    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public List<Permission> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    @GetMapping("/{id}")
    public Optional<Permission> getPermissionById(@PathVariable Long id) {
        return permissionService.getPermissionById(id);
    }

    @PostMapping
    public Permission createPermission(@RequestBody Permission permission) {
        return permissionService.savePermission(permission);
    }

    @PutMapping("/{id}")
    public Permission updatePermission(@PathVariable Long id, @RequestBody Permission updatedPermission) {
        return permissionService.updatePermission(id, updatedPermission);
    }

    @DeleteMapping("/{id}")
    public String deletePermission(@PathVariable Long id) {
        boolean deleted = permissionService.deletePermission(id);
        return deleted ? "Permiso eliminado correctamente" : "No se encontró el permiso con ID: " + id;
    }

    @PostMapping("/{permissionId}/assign/{userId}")
    public ResponseEntity<String> assignPermission(@PathVariable Long permissionId, @PathVariable Long userId) {
        boolean assigned = permissionService.assignPermissionToUser(permissionId, userId);
        return assigned ? ResponseEntity.ok("Permiso asignado correctamente.") :
                ResponseEntity.badRequest().body("No se pudo asignar el permiso.");
    }

    @PostMapping("/{permissionId}/revoke/{userId}")
    public ResponseEntity<String> revokePermission(@PathVariable Long permissionId, @PathVariable Long userId) {
        boolean revoked = permissionService.revokePermissionFromUser(permissionId, userId);
        return revoked ? ResponseEntity.ok("Permiso revocado correctamente.") :
                ResponseEntity.badRequest().body("No se pudo revocar el permiso.");
    }
}