package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.User;
import co.edu.udes.activity.backend.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


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
        boolean changed = userService.changePassword(id, newPassword).getStatus();
        return changed ? ResponseEntity.ok("Password updated") : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? "Usuario eliminado correctamente" : "No se encontró el usuario con ID: " + id;
    }

    @PutMapping("/{id}/assign-role/{roleId}")
    public ResponseEntity<String> assignRole(@PathVariable Long id, @PathVariable Long roleId) {
        boolean assigned = userService.assignRole(id, roleId).getStatus();
        return assigned ? ResponseEntity.ok("Role assigned") : ResponseEntity.badRequest().body("Failed to assign role");
    }


}
