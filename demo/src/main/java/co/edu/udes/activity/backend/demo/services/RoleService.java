package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.RoleDTO;
import co.edu.udes.activity.backend.demo.dto.RoleRequestDTO;
import co.edu.udes.activity.backend.demo.models.Permission;
import co.edu.udes.activity.backend.demo.repositories.PermissionRepository;
import co.edu.udes.activity.backend.demo.repositories.RoleRepository;
import co.edu.udes.activity.backend.demo.models.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(role -> modelMapper.map(role, RoleDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<RoleDTO> getRoleById(Long id) {
        return roleRepository.findById(id)
                .map(role -> modelMapper.map(role, RoleDTO.class));
    }

    public RoleDTO saveRole(RoleRequestDTO dto) {
        Role role = new Role();
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());

        Set<Permission> permissions = dto.getPermissionsId().stream()
                .map(permissionRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        role.setPermissions(permissions);

        return modelMapper.map(roleRepository.save(role), RoleDTO.class);
    }

    public RoleDTO updateRole(Long id, RoleRequestDTO dto) {
        return roleRepository.findById(id).map(role -> {
            role.setName(dto.getName());
            role.setDescription(dto.getDescription());

            Set<Permission> permissions = dto.getPermissionsId().stream()
                    .map(permissionRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());

            role.setPermissions(permissions);
            return modelMapper.map(roleRepository.save(role), RoleDTO.class);
        }).orElse(null);
    }

    public boolean deleteRole(Long id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public RoleDTO addPermissionToRole(Long roleId, Long permissionId) {
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        Optional<Permission> optionalPermission = permissionRepository.findById(permissionId);

        if (optionalRole.isPresent() && optionalPermission.isPresent()) {
            Role role = optionalRole.get();
            role.getPermissions().add(optionalPermission.get());
            return modelMapper.map(roleRepository.save(role), RoleDTO.class);
        }
        return null;
    }

    public RoleDTO removePermissionFromRole(Long roleId, Long permissionId) {
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        Optional<Permission> optionalPermission = permissionRepository.findById(permissionId);

        if (optionalRole.isPresent() && optionalPermission.isPresent()) {
            Role role = optionalRole.get();
            role.getPermissions().remove(optionalPermission.get());
            return modelMapper.map(roleRepository.save(role), RoleDTO.class);
        }
        return null;
    }

    public List<Permission> getPermissionsByRole(Long roleId) {
        return roleRepository.findById(roleId)
                .map(role -> new ArrayList<>(role.getPermissions()))
                .orElse(null);
    }
}
