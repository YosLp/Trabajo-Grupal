package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.AuthenticationDTO;
import co.edu.udes.activity.backend.demo.dto.AuthenticationRequestDTO;
import co.edu.udes.activity.backend.demo.models.Authentication;
import co.edu.udes.activity.backend.demo.models.User;
import co.edu.udes.activity.backend.demo.repositories.AuthenticationRepository;
import co.edu.udes.activity.backend.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<AuthenticationDTO> getAllAuthentications() {
        return authenticationRepository.findAll().stream()
                .map(auth -> {
                    AuthenticationDTO dto = modelMapper.map(auth, AuthenticationDTO.class);
                    dto.setUserId(auth.getUser().getId());
                    return dto;
                }).collect(Collectors.toList());
    }

    public Optional<AuthenticationDTO> getAuthenticationById(Long id) {
        return authenticationRepository.findById(id)
                .map(auth -> {
                    AuthenticationDTO dto = modelMapper.map(auth, AuthenticationDTO.class);
                    dto.setUserId(auth.getUser().getId());
                    return dto;
                });
    }

    public AuthenticationDTO saveAuthentication(AuthenticationRequestDTO requestDTO) {
        Authentication auth = modelMapper.map(requestDTO, Authentication.class);
        userRepository.findById(requestDTO.getUserId()).ifPresent(auth::setUser);
        return modelMapper.map(authenticationRepository.save(auth), AuthenticationDTO.class);
    }

    public AuthenticationDTO updateAuthentication(Long id, AuthenticationRequestDTO requestDTO) {
        return authenticationRepository.findById(id).map(auth -> {
            auth.setSessionToken(requestDTO.getSessionToken());
            auth.setExpirationDate(requestDTO.getExpirationDate());
            auth.setFailedAttempts(requestDTO.getFailedAttempts());
            auth.setLocked(requestDTO.isLocked());
            userRepository.findById(requestDTO.getUserId()).ifPresent(auth::setUser);
            return modelMapper.map(authenticationRepository.save(auth), AuthenticationDTO.class);
        }).orElse(null);
    }

    public boolean deleteAuthentication(Long id) {
        if (authenticationRepository.existsById(id)) {
            authenticationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public AuthenticationDTO login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Optional<Authentication> authOpt = authenticationRepository.findByUser(user);
            Authentication authentication = authOpt.orElse(new Authentication());
            authentication.setUser(user);

            if (authentication.isLocked()) {
                throw new RuntimeException("La cuenta está bloqueada.");
            }

            if (user.getPassword().equals(password)) {
                authentication.setFailedAttempts(0);
                authentication.setLocked(false);
                authentication.setSessionToken(UUID.randomUUID().toString());
                authentication.setExpirationDate(LocalDateTime.now().plusHours(1));
                authenticationRepository.save(authentication);
                return modelMapper.map(authentication, AuthenticationDTO.class);
            } else {
                int intentos = authentication.getFailedAttempts() + 1;
                authentication.setFailedAttempts(intentos);
                if (intentos >= 3) {
                    authentication.setLocked(true);
                }
                authenticationRepository.save(authentication);
                throw new RuntimeException("Contraseña incorrecta.");
            }
        } else {
            throw new RuntimeException("Usuario no encontrado.");
        }
    }

    public boolean logout(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            Optional<Authentication> authOpt = authenticationRepository.findByUser(userOpt.get());
            if (authOpt.isPresent()) {
                Authentication auth = authOpt.get();
                auth.setSessionToken(null);
                auth.setExpirationDate(null);
                authenticationRepository.save(auth);
                return true;
            }
        }
        return false;
    }

    public boolean recoverPassword(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String temporaryPassword = UUID.randomUUID().toString().substring(0, 8);
            user.setPassword(passwordEncoder.encode(temporaryPassword));
            userRepository.save(user);
            System.out.println("Contraseña temporal: " + temporaryPassword);
            return true;
        }
        return false;
    }
}



