package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Classroom;
import co.edu.udes.activity.backend.demo.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Optional<Classroom> getClassroomById(Integer id) {
        return classroomRepository.findById(id);
    }

    public Classroom saveClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public Classroom updateClassroom(Integer id, Classroom updatedClassroom) {
        return classroomRepository.findById(id).map(classroom -> {
            classroom.setLocation(updatedClassroom.getLocation());
            classroom.setCapacity(updatedClassroom.getCapacity());
            return classroomRepository.save(classroom);
        }).orElse(null);
    }

    public boolean deleteClassroom(Integer id) {
        if (classroomRepository.existsById(id)) {
            classroomRepository.deleteById(id);
            return true;
        }
        return false;
    }
}