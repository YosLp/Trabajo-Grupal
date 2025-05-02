package co.edu.udes.activity.backend.demo.repositories;

import co.edu.udes.activity.backend.demo.models.Group;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Collection<Object> findBySubjectId(Long subjectId);
    Collection<Object> findByTeacherId(Long teacherId);
}
