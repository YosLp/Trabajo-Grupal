package co.edu.udes.activity.backend.demo.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.udes.activity.backend.demo.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
}
