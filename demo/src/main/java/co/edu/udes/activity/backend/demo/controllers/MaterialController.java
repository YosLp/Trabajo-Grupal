package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Material;
import co.edu.udes.activity.backend.demo.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {
    
    @Autowired
    private MaterialService materialService;

    @GetMapping
    public List<Material> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable Long id) {
        return materialService.getMaterialById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Material createMaterial(@RequestBody Material material) {
        return materialService.saveMaterial(material);
    }

    @PutMapping("/{id}")
    public Material updateMaterial(@PathVariable Long id, @RequestBody Material updatedMaterial) {
        return materialService.updateMaterial(id, updatedMaterial);
    }

    @DeleteMapping("/{id}")
    public String deleteMaterial(@PathVariable Long id) {
        boolean deleted = materialService.deleteMaterial(id);
        return deleted ? "Material eliminado correctamente" : "No se encontró el material con ID: " + id;
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Material> updateStatus(@PathVariable Long id, @RequestBody boolean status) {
        Material updated = materialService.updateStatus(id, status);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/available")
    public ResponseEntity<Boolean> checkAvailability(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.checkAvailability(id));
    }

    @PutMapping("/{id}/increase")
    public ResponseEntity<Material> increaseAmount(@PathVariable Long id, @RequestParam int amount) {
        Material updated = materialService.increaseAmount(id, amount);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

}