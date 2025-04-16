package co.edu.udes.activity.backend.demo.repositories;

import co.edu.udes.activity.backend.demo.models.Authentication;
import co.edu.udes.activity.backend.demo.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository <Authentication,Long>{
    Optional<Authentication> findByUser(User user);
    
} 
