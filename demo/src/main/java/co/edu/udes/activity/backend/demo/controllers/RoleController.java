package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Permission;
import co.edu.udes.activity.backend.demo.models.Role;
import co.edu.udes.activity.backend.demo.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Optional<Role> getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role updatedRole) {
        return roleService.updateRole(id, updatedRole);
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable Long id) {
        boolean deleted = roleService.deleteRole(id);
        return deleted ? "Rol eliminado correctamente" : "No se encontró el rol con ID: " + id;
    }

    @PostMapping("/{roleId}/permissions")
    public ResponseEntity<Role> addPermission(@PathVariable Long roleId, @RequestBody Permission permission) {
        Role updatedRole = roleService.addPermissionToRole(roleId, permission);
        return updatedRole != null ? ResponseEntity.ok(updatedRole) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{roleId}/permissions")
    public ResponseEntity<Role> removePermission(@PathVariable Long roleId, @RequestBody Permission permission) {
        Role updatedRole = roleService.removePermissionFromRole(roleId, permission);
        return updatedRole != null ? ResponseEntity.ok(updatedRole) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{roleId}/permissions")
    public ResponseEntity<List<Permission>> getPermissions(@PathVariable Long roleId) {
        List<Permission> permissions = roleService.getPermissionsByRole(roleId);
        return permissions != null ? ResponseEntity.ok(permissions) : ResponseEntity.notFound().build();
    }
}
