package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.PermissionDTO;
import co.edu.udes.activity.backend.demo.dto.PermissionRequestDTO;
import co.edu.udes.activity.backend.demo.models.Permission;
import co.edu.udes.activity.backend.demo.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<PermissionDTO> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDTO> getPermissionById(@PathVariable Long id) {
        PermissionDTO dto = permissionService.getPermissionById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PermissionDTO> createPermission(@RequestBody PermissionRequestDTO requestDTO) {
        PermissionDTO dto = permissionService.savePermission(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionDTO> updatePermission(@PathVariable Long id, @RequestBody PermissionRequestDTO requestDTO) {
        PermissionDTO dto = permissionService.updatePermission(id, requestDTO);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePermission(@PathVariable Long id) {
        boolean deleted = permissionService.deletePermission(id);
        return deleted ? ResponseEntity.ok("Permiso eliminado correctamente") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el permiso con ID: " + id);
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
