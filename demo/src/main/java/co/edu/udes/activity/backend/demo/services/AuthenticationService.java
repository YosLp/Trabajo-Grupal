package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Authentication;
import co.edu.udes.activity.backend.demo.repositories.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;

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
}
