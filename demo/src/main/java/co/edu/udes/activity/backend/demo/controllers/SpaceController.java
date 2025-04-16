package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Space;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.udes.activity.backend.demo.services.SpaceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spaces")
public class SpaceController {
    
    @Autowired
    private SpaceService spaceService;

    @GetMapping
    public List<Space> getAllSpaces() {
        return spaceService.getAllSpaces();
    }

    @GetMapping("/{id}")
    public Optional<Space> getSpaceById(@PathVariable Long id) {
        return spaceService.getSpaceById(id);
    }

    @PostMapping
    public Space createSpace(@RequestBody Space space) {
        return spaceService.saveSpace(space);
    }

    @PutMapping("/{id}")
    public Space updateSpace(@PathVariable Long id, @RequestBody Space updatedSpace) {
        return spaceService.updateSpace(id, updatedSpace);
    }

    @DeleteMapping("/{id}")
    public String deleteSpace(@PathVariable Long id) {
        boolean deleted = spaceService.deleteSpace(id);
        return deleted ? "Espacio eliminado correctamente" : "No se encontró el espacio con ID: " + id;
    }

    @GetMapping("/availability/{id}")
    public ResponseEntity<Boolean> checkAvailability(@PathVariable Long id) {
        return ResponseEntity.ok(spaceService.checkAvailability(id));
    }

    @PutMapping("/availability/{id}")
    public ResponseEntity<String> updateAvailability(@PathVariable Long id,
                                                     @RequestParam boolean available) {
        boolean updated = spaceService.updateAvailability(id, available);
        return updated ?
                ResponseEntity.ok("Disponibilidad actualizada") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Espacio no encontrado");
    }
}