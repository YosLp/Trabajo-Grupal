package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Authentication;
import co.edu.udes.activity.backend.demo.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.udes.activity.backend.demo.models.Authentication;
import co.edu.udes.activity.backend.demo.services.AuthenticationService;


@RestController
@RequestMapping("/api/authentications")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    public List<Authentication> getAllAuthentications() {
        return authenticationService.getAllAuthentications();
    }

    @GetMapping("/{id}")
    public Optional<Authentication> getAuthenticationById(@PathVariable Long id) {
        return authenticationService.getAuthenticationById(id);
    }

    @PostMapping
    public Authentication createAuthentication(@RequestBody Authentication authentication) {
        return authenticationService.saveAuthentication(authentication);
    }

    @PutMapping("/{id}")
    public Authentication updateAuthentication(@PathVariable Long id, @RequestBody Authentication updatedAuthentication) {
        return authenticationService.updateAuthentication(id, updatedAuthentication);
    }

    @DeleteMapping("/{id}")
    public String deleteAuthentication(@PathVariable Long id) {
        boolean deleted = authenticationService.deleteAuthentication(id);
        return deleted ? "Autenticación eliminada correctamente" : "No se encontró la autenticación con ID: " + id;
    }

    @PostMapping("/login")
    public ResponseEntity<Authentication> login(@RequestParam String email,
                                                @RequestParam String password) {
        try {
            Authentication auth = authenticationService.login(email, password);
            return ResponseEntity.ok(auth);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/logout/{userId}")
    public ResponseEntity<?> logout(@PathVariable Long userId) {
        boolean result = authenticationService.logout(userId);
        if (result) {
            return ResponseEntity.ok("Sesión cerrada correctamente");
        } else {
            return ResponseEntity.badRequest().body("No se pudo cerrar la sesión");
        }
    }

    @PostMapping("/recover-password")
    public ResponseEntity<String> recoverPassword(@RequestParam String email) {
        boolean result = authenticationService.recoverPassword(email);
        if (result) {
            return ResponseEntity.ok("Correo de recuperación enviado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Correo no encontrado");
    }
}