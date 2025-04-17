package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.SpaceDTO;
import co.edu.udes.activity.backend.demo.dto.SpaceRequestDTO;
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
    public List<SpaceDTO> getAllSpaces() {
        return spaceService.getAllSpaces();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceDTO> getSpaceById(@PathVariable Long id) {
        SpaceDTO dto = spaceService.getSpaceById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<SpaceDTO> createSpace(@RequestBody SpaceRequestDTO dto) {
        return ResponseEntity.ok(spaceService.saveSpace(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpaceDTO> updateSpace(@PathVariable Long id, @RequestBody SpaceRequestDTO dto) {
        SpaceDTO updated = spaceService.updateSpace(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpace(@PathVariable Long id) {
        boolean deleted = spaceService.deleteSpace(id);
        return deleted ?
                ResponseEntity.ok("Espacio eliminado correctamente") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el espacio con ID: " + id);
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
