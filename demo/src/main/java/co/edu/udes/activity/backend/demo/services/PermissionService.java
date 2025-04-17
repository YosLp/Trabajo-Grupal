package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.PermissionDTO;
import co.edu.udes.activity.backend.demo.dto.PermissionRequestDTO;
import co.edu.udes.activity.backend.demo.repositories.PermissionRepository;
import co.edu.udes.activity.backend.demo.models.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.udes.activity.backend.demo.models.User;
import co.edu.udes.activity.backend.demo.repositories.UserRepository;
@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<PermissionDTO> getAllPermissions() {
        return permissionRepository.findAll().stream()
                .map(permission -> modelMapper.map(permission, PermissionDTO.class))
                .collect(Collectors.toList());
    }

    public PermissionDTO getPermissionById(Long id) {
        return permissionRepository.findById(id)
                .map(permission -> modelMapper.map(permission, PermissionDTO.class))
                .orElse(null);
    }

    public PermissionDTO savePermission(PermissionRequestDTO permissionRequestDTO) {
        Permission permission = modelMapper.map(permissionRequestDTO, Permission.class);
        return modelMapper.map(permissionRepository.save(permission), PermissionDTO.class);
    }

    public PermissionDTO updatePermission(Long id, PermissionRequestDTO updatedDTO) {
        return permissionRepository.findById(id).map(permission -> {
            permission.setName(updatedDTO.getName());
            permission.setDescription(updatedDTO.getDescription());
            return modelMapper.map(permissionRepository.save(permission), PermissionDTO.class);
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
