package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Authentication;
import co.edu.udes.activity.backend.demo.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

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