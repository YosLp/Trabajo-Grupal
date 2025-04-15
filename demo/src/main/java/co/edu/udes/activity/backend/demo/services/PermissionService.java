package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.repositories.PermissionRepository;
import co.edu.udes.activity.backend.demo.models.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

    @Autowired
<<<<<<< HEAD
    public PermissionRepository permissionRepository;
=======
    private PermissionRepository permissionRepository;
>>>>>>> feature-lusbin

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public Optional<Permission> getPermissionById(Long id) {
        return permissionRepository.findById(id);
    }

    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission updatePermission(Long id, Permission updatedPermission) {
        return permissionRepository.findById(id).map(permission -> {
            permission.setName(updatedPermission.getName());
            permission.setDescription(updatedPermission.getDescription());
            return permissionRepository.save(permission);
        }).orElse(null);
    }

    public boolean deletePermission(Long id) {
        if (permissionRepository.existsById(id)) {
            permissionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}