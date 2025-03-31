package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Material;
import co.edu.udes.activity.backend.demo.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

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
    public Optional<Material> getMaterialById(@PathVariable Long id) {
        return materialService.getMaterialById(id);
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
}