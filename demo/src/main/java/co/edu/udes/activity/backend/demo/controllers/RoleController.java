package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.RoleDTO;
import co.edu.udes.activity.backend.demo.dto.RoleRequestDTO;
import co.edu.udes.activity.backend.demo.models.Permission;

import co.edu.udes.activity.backend.demo.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleRequestDTO dto) {
        RoleDTO created = roleService.saveRole(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @RequestBody RoleRequestDTO dto) {
        RoleDTO updated = roleService.updateRole(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        boolean deleted = roleService.deleteRole(id);
        return deleted ? ResponseEntity.ok("Rol eliminado correctamente")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el rol con ID: " + id);
    }

    @PostMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<RoleDTO> addPermission(@PathVariable Long roleId, @PathVariable Long permissionId) {
        RoleDTO updated = roleService.addPermissionToRole(roleId, permissionId);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<RoleDTO> removePermission(@PathVariable Long roleId, @PathVariable Long permissionId) {
        RoleDTO updated = roleService.removePermissionFromRole(roleId, permissionId);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{roleId}/permissions")
    public ResponseEntity<List<Permission>> getPermissions(@PathVariable Long roleId) {
        List<Permission> permissions = roleService.getPermissionsByRole(roleId);
        return permissions != null ? ResponseEntity.ok(permissions) : ResponseEntity.notFound().build();
    }
}

