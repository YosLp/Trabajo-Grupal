package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.MaterialDTO;
import co.edu.udes.activity.backend.demo.dto.MaterialRequestDTO;
import co.edu.udes.activity.backend.demo.models.Material;
import co.edu.udes.activity.backend.demo.services.MaterialService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<MaterialDTO> getAllMaterials() {
        return materialService.getAllMaterials()
                .stream()
                .map(material -> modelMapper.map(material, MaterialDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDTO> getMaterialById(@PathVariable Long id) {
        return materialService.getMaterialById(id)
                .map(material -> ResponseEntity.ok(modelMapper.map(material, MaterialDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MaterialDTO createMaterial(@RequestBody MaterialRequestDTO requestDTO) {
        Material material = modelMapper.map(requestDTO, Material.class);
        Material saved = materialService.saveMaterial(material);
        return modelMapper.map(saved, MaterialDTO.class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialDTO> updateMaterial(@PathVariable Long id, @RequestBody MaterialRequestDTO updatedDTO) {
        Material updatedMaterial = modelMapper.map(updatedDTO, Material.class);
        Material updated = materialService.updateMaterial(id, updatedMaterial);
        return updated != null ?
                ResponseEntity.ok(modelMapper.map(updated, MaterialDTO.class)) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMaterial(@PathVariable Long id) {
        boolean deleted = materialService.deleteMaterial(id);
        return deleted ?
                ResponseEntity.ok("Material eliminado correctamente") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el material con ID: " + id);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<MaterialDTO> updateStatus(@PathVariable Long id, @RequestBody boolean status) {
        Material updated = materialService.updateStatus(id, status);
        return updated != null ?
                ResponseEntity.ok(modelMapper.map(updated, MaterialDTO.class)) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/available")
    public ResponseEntity<Boolean> checkAvailability(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.checkAvailability(id));
    }

    @PutMapping("/{id}/increase")
    public ResponseEntity<MaterialDTO> increaseAmount(@PathVariable Long id, @RequestParam int amount) {
        Material updated = materialService.increaseAmount(id, amount);
        return updated != null ?
                ResponseEntity.ok(modelMapper.map(updated, MaterialDTO.class)) :
                ResponseEntity.notFound().build();
    }
}