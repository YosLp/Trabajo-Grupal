package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.UserDTO;
import co.edu.udes.activity.backend.demo.dto.UserRequestDTO;
import co.edu.udes.activity.backend.demo.dto.UserWPasswordDto;
import co.edu.udes.activity.backend.demo.models.Role;
import co.edu.udes.activity.backend.demo.models.User;
import co.edu.udes.activity.backend.demo.repositories.RoleRepository;
import co.edu.udes.activity.backend.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::convertToDTO).orElse(null);
    }

    public UserWPasswordDto getUserWithPasswordByDocumentNumber(String documentNumber) {
        Optional<User> user = userRepository.findByDocumentNumber(documentNumber);
        return user.map(this::convertToUserWPasswordDto).orElse(null);
    }

    private UserWPasswordDto convertToUserWPasswordDto(User user) {
        UserWPasswordDto dto = new UserWPasswordDto();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword()); // ← ahora sí se incluye
        dto.setStatus(user.getStatus());
        dto.setRoleId(user.getRole().getId());
        dto.setDocumentNumber(user.getDocumentNumber());
        return dto;
    }

    public User saveUser(UserRequestDTO userDTO) {

        User user = new User();
        System.out.println("Documento: " + userDTO.getDocumentNumber());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setStatus(userDTO.getStatus());
        user.setDocumentNumber(userDTO.getDocumentNumber());

        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        user.setRole(role);

        return userRepository.save(user);
    }

    public UserDTO updateUser(Long id, UserRequestDTO updatedDTO) {
        return userRepository.findById(id).map(user -> {
            user.setFirstName(updatedDTO.getFirstName());
            user.setLastName(updatedDTO.getLastName());
            user.setEmail(updatedDTO.getEmail());
            user.setPassword(updatedDTO.getPassword());
            user.setStatus(updatedDTO.getStatus());
            roleRepository.findById(updatedDTO.getRoleId()).ifPresent(user::setRole);
            return convertToDTO(userRepository.save(user));
        }).orElse(null);
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean authenticate(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent() && user.get().getPassword().equals(password);
    }

    public boolean changePassword(Long id, String newPassword) {
        return userRepository.findById(id).map(user -> {
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }).orElse(false);
    }

    public boolean assignRole(Long id, Long idRole) {
        Optional<User> user = userRepository.findById(id);
        Optional<Role> role = roleRepository.findById(idRole);
        if (user.isPresent() && role.isPresent()) {
            user.get().setRole(role.get());
            userRepository.save(user.get());
            return true;
        }
        return false;
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = modelMapper.map(user, UserDTO.class);
        dto.setRoleName(user.getRole().getName());
        return dto;
    }

    private User convertToEntity(UserRequestDTO dto) {
        return modelMapper.map(dto, User.class);
    }
}

