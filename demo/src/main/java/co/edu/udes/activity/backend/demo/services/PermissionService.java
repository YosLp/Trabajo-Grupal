package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.repositories.PermissionRepository;
import co.edu.udes.activity.backend.demo.models.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import co.edu.udes.activity.backend.demo.models.User;
import co.edu.udes.activity.backend.demo.repositories.UserRepository;

@Service
public class PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    private UserRepository userRepository;

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

    public boolean assignPermissionToUser(Long permissionId, Long userId) {
        Optional<Permission> optPermission = permissionRepository.findById(permissionId);
        Optional<User> optUser = userRepository.findById(userId);
        if (optPermission.isPresent() && optUser.isPresent()) {
            User user = optUser.get();
            Permission permission = optPermission.get();
            user.getRole().getPermissions().add(permission);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean revokePermissionFromUser(Long permissionId, Long userId) {
        Optional<Permission> optPermission = permissionRepository.findById(permissionId);
        Optional<User> optUser = userRepository.findById(userId);
        if (optPermission.isPresent() && optUser.isPresent()) {
            User user = optUser.get();
            Permission permission = optPermission.get();
            user.getRole().getPermissions().remove(permission);
            userRepository.save(user);
            return true;
        }
        return false;
    }

}