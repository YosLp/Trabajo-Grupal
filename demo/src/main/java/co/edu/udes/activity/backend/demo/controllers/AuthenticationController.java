package co.edu.udes.activity.backend.demo.controllers;

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
}