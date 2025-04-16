package co.edu.udes.activity.backend.demo.repositories;

import co.edu.udes.activity.backend.demo.models.Messaging;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface MessagingRepository extends JpaRepository <Messaging,Long>{

    List<Messaging> findByReceiverId(Long userId);
}

