package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Authentication;
import co.edu.udes.activity.backend.demo.models.User;
import co.edu.udes.activity.backend.demo.repositories.AuthenticationRepository;
import co.edu.udes.activity.backend.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            // Comparar la contraseña directamente (sin encriptación)
            if (user.getPassword().equals(password)) {
                // Lógica para generar sesión y asignar el token de sesión
                Authentication authentication = new Authentication();
                authentication.setUser(user);
                // Establecer los datos de la sesión
                authentication.setSessionToken("someGeneratedToken"); // Aquí debes generar un token real
                authentication.setExpirationDate(java.time.LocalDateTime.now().plusHours(1)); // Ejemplo de expiración
                authentication.setFailedAttempts(0);
                authentication.setLocked(false);

                return authentication; // Retorna el objeto de sesión
            } else {
                throw new RuntimeException("Contraseña incorrecta");
            }
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    public void logout(String sessionToken) {
        // Lógica para invalidar sesión (si se utiliza un token de sesión)
        // Aquí podrías buscar el token de sesión y eliminarlo
        System.out.println("Sesión con token " + sessionToken + " cerrada.");
    }

    public void recoverPassword(String email, String newPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(newPassword); // Establecer la nueva contraseña
            userRepository.save(user); // Guardar el usuario con la nueva contraseña
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

}
