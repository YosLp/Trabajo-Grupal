package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Authentication;
import co.edu.udes.activity.backend.demo.models.User;
import co.edu.udes.activity.backend.demo.repositories.AuthenticationRepository;
import co.edu.udes.activity.backend.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Authentication> getAllAuthentications() {
        return authenticationRepository.findAll();
    }

    public Optional<Authentication> getAuthenticationById(Long id) {
        return authenticationRepository.findById(id);
    }

    public Authentication saveAuthentication(Authentication authentication) {
        return authenticationRepository.save(authentication);
    }

    public Authentication updateAuthentication(Long id, Authentication updatedAuthentication) {
        return authenticationRepository.findById(id).map(auth -> {
            auth.setSessionToken(updatedAuthentication.getSessionToken());
            auth.setExpirationDate(updatedAuthentication.getExpirationDate());
            auth.setFailedAttempts(updatedAuthentication.getFailedAttempts());
            auth.setLocked(updatedAuthentication.isLocked());
            auth.setUser(updatedAuthentication.getUser());
            return authenticationRepository.save(auth);
        }).orElse(null);
    }

    public boolean deleteAuthentication(Long id) {
        if (authenticationRepository.existsById(id)) {
            authenticationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Authentication login(String email, String password) {
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
                return authentication;
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
            String temporaryPassword = UUID.randomUUID().toString().substring(0, 8); // contraseña temporal
            user.setPassword(passwordEncoder.encode(temporaryPassword));
            userRepository.save(user);
            System.out.println("Contraseña temporal: " + temporaryPassword); // Simula envío
            return true;
        }
        return false;
    }
}


