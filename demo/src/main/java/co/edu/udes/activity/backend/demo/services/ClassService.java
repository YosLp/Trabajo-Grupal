package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Class;
import co.edu.udes.activity.backend.demo.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public Optional<Class> getClassById(Integer id) {
        return classRepository.findById(id);
    }

    public Class saveClass(Class clazz) {
        return classRepository.save(clazz);
    }

    public Class updateClass(Integer id, Class updatedClass) {
        return classRepository.findById(id).map(existingClass -> {
            existingClass.setStarHour(updatedClass.getStarHour());
            existingClass.setEndHour(updatedClass.getEndHour());
            existingClass.setDescription(updatedClass.getDescription());
            existingClass.setGroup(updatedClass.getGroup());
            existingClass.setSchedule(updatedClass.getSchedule());
            existingClass.setClassroom(updatedClass.getClassroom());
            return classRepository.save(existingClass);
        }).orElse(null);
    }

    public boolean deleteClass(Integer id) {
        if (classRepository.existsById(id)) {
            classRepository.deleteById(id);
            return true;
        }
        return false;
    }
}