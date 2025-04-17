package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.AuthenticationDTO;
import co.edu.udes.activity.backend.demo.dto.AuthenticationRequestDTO;
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
    public List<AuthenticationDTO> getAllAuthentications() {
        return authenticationService.getAllAuthentications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthenticationDTO> getAuthenticationById(@PathVariable Long id) {
        return authenticationService.getAuthenticationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AuthenticationDTO> createAuthentication(@RequestBody AuthenticationRequestDTO requestDTO) {
        AuthenticationDTO saved = authenticationService.saveAuthentication(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthenticationDTO> updateAuthentication(@PathVariable Long id, @RequestBody AuthenticationRequestDTO requestDTO) {
        AuthenticationDTO updated = authenticationService.updateAuthentication(id, requestDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthentication(@PathVariable Long id) {
        boolean deleted = authenticationService.deleteAuthentication(id);
        return deleted ? ResponseEntity.ok("Autenticación eliminada correctamente") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la autenticación con ID: " + id);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDTO> login(@RequestParam String email, @RequestParam String password) {
        try {
            AuthenticationDTO auth = authenticationService.login(email, password);
            return ResponseEntity.ok(auth);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/logout/{userId}")
    public ResponseEntity<String> logout(@PathVariable Long userId) {
        boolean result = authenticationService.logout(userId);
        return result ? ResponseEntity.ok("Sesión cerrada correctamente")
                : ResponseEntity.badRequest().body("No se pudo cerrar la sesión");
    }

    @PostMapping("/recover-password")
    public ResponseEntity<String> recoverPassword(@RequestParam String email) {
        boolean result = authenticationService.recoverPassword(email);
        return result ? ResponseEntity.ok("Correo de recuperación enviado")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Correo no encontrado");
    }
}
