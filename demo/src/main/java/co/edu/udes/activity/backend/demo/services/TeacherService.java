package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Teacher;
import co.edu.udes.activity.backend.demo.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher updatedTeacher) {
        return teacherRepository.findById(id).map(teacher -> {
            teacher.setFirstName(updatedTeacher.getFirstName());
            teacher.setLastName(updatedTeacher.getLastName());
            teacher.setEmail(updatedTeacher.getEmail());
            teacher.setPassword(updatedTeacher.getPassword());
            teacher.setStatus(updatedTeacher.getStatus());
            teacher.setRole(updatedTeacher.getRole());
            teacher.setSpecialization(updatedTeacher.getSpecialization());
            return teacherRepository.save(teacher);
        }).orElse(null);
    }

    public boolean deleteTeacher(Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
