package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Course;
import co.edu.udes.activity.backend.demo.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

   
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

   
    public Optional<Course> getCourseById(int id) {
        return courseRepository.findById(id);
    }

   
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    
    public Course updateCourse(int id, Course updatedCourse) {
        return courseRepository.findById(id).map(course -> {
            course.setName(updatedCourse.getName());
            course.setDescription(updatedCourse.getDescription());
            course.setStarDate(updatedCourse.getStarDate());
            course.setEndDate(updatedCourse.getEndDate());
            course.setContent(updatedCourse.getContent());
            course.setObjetives(updatedCourse.getObjetives());
            course.setCompetencies(updatedCourse.getCompetencies());
            course.setCapacity(updatedCourse.getCapacity());
            course.setCareer(updatedCourse.getCareer());
            course.setSubject(updatedCourse.getSubject());
            course.setPeriod(updatedCourse.getPeriod());
            return courseRepository.save(course);
        }).orElse(null);
    }

 
    public boolean deleteCourse(int id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
