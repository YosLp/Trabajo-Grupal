package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.UserDTO;
import co.edu.udes.activity.backend.demo.dto.UserRequestDTO;
import co.edu.udes.activity.backend.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;



@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<Boolean> authenticateUser(@RequestParam String email, @RequestParam String password) {
        boolean isAuthenticated = userService.authenticate(email, password);
        return ResponseEntity.ok(isAuthenticated);
    }

    @PutMapping("/{id}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable Long id, @RequestBody String newPassword) {
        boolean changed = userService.changePassword(id, newPassword);
        return changed ? ResponseEntity.ok("Password updated") : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserDTO userDTO = userService.saveUser(userRequestDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        UserDTO updated = userService.updateUser(id, userRequestDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? ResponseEntity.ok("Usuario eliminado correctamente") : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/assign-role/{roleId}")
    public ResponseEntity<String> assignRole(@PathVariable Long id, @PathVariable Long roleId) {
        boolean assigned = userService.assignRole(id, roleId);
        return assigned ? ResponseEntity.ok("Rol asignado") : ResponseEntity.badRequest().body("No se pudo asignar el rol");
    }
}

