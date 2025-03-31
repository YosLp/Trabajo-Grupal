package co.edu.udes.activity.backend.demo.repositories;


import co.edu.udes.activity.backend.demo.models.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface SpaceRepository extends JpaRepository<Space, Long> {
        
    }
