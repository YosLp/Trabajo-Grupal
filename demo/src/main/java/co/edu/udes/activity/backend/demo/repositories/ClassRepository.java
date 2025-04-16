package co.edu.udes.activity.backend.demo.repositories;

import co.edu.udes.activity.backend.demo.models.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {
}