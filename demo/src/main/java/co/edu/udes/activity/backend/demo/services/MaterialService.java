package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Material;
import co.edu.udes.activity.backend.demo.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    public Optional<Material> getMaterialById(Long id) {
        return materialRepository.findById(id);
    }

    public Material saveMaterial(Material material) {
        return materialRepository.save(material);
    }

    public Material updateMaterial(Long id, Material updatedMaterial) {
        return materialRepository.findById(id).map(material -> {
            material.setName(updatedMaterial.getName());
            material.setType(updatedMaterial.getType());
            material.setAmount(updatedMaterial.getAmount());
            material.setAvailable(updatedMaterial.isAvailable());
            return materialRepository.save(material);
        }).orElse(null);
    }

    public boolean deleteMaterial(Long id) {
        if (materialRepository.existsById(id)) {
            materialRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Material updateStatus(Long id, boolean status) {
        return materialRepository.findById(id).map(material -> {
            material.setAvailable(status);
            return materialRepository.save(material);
        }).orElse(null);
    }

    public boolean checkAvailability(Long id) {
        return materialRepository.findById(id)
                .map(material -> material.getAmount() > 0)
                .orElse(false);
    }

    public Material increaseAmount(Long id, int amountToAdd) {
        return materialRepository.findById(id).map(material -> {
            material.setAmount(material.getAmount() + amountToAdd);
            return materialRepository.save(material);
        }).orElse(null);
    }
}

