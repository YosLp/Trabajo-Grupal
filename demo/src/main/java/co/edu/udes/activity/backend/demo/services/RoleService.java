package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Permission;
import co.edu.udes.activity.backend.demo.repositories.RoleRepository;
import co.edu.udes.activity.backend.demo.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role updatedRole) {
        return roleRepository.findById(id).map(role -> {
            role.setName(updatedRole.getName());
            role.setDescription(updatedRole.getDescription());
            role.setPermissions(updatedRole.getPermissions());
            return roleRepository.save(role);
        }).orElse(null);
    }

    public boolean deleteRole(Long id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Role addPermissionToRole (Long roleId, Permission permission) {
        Optional<Role> optionalRole= roleRepository.findById(roleId);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            role.getPermissions().add(permission);
            return roleRepository.save(role);

        }
        return null;
    }

    public Role removePermissionFromRole (Long roleId, Permission permission) {
        Optional<Role> optionalRole= roleRepository.findById(roleId);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            role.getPermissions().remove(permission);
            return roleRepository.save(role);


        }
        return null;
    }

    public List<Permission> getPermissionsByRole(Long roleId) {
        Optional<Role> optionalRole = roleRepository.findById(roleId);

        return optionalRole
                .map(role -> new ArrayList<>(role.getPermissions()))
                .orElse(null);
    }

}