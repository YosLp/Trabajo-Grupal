package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Student;
import co.edu.udes.activity.backend.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
=======
    public Student registerStudent(Student student) {
>>>>>>> Stashed changes
        return studentRepository.save(student);
    }

    public Student updateStudent(int id, Student updatedStudent) {
=======
    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(long id, Student updatedStudent) {
>>>>>>> Stashed changes
        return studentRepository.findById(id).map(student -> {
            student.setAddress(updatedStudent.getAddress());
            student.setStatusStudent(updatedStudent.getStatusStudent());
            student.setRegistrationDate(updatedStudent.getRegistrationDate());
            student.setPhoneNumber(updatedStudent.getPhoneNumber());
            return studentRepository.save(student);
        }).orElse(null); // Retorna null si el estudiante no existe
    }

<<<<<<< Updated upstream
    public boolean deleteStudent(int id) {
=======
    public boolean deleteStudent(long id) {
>>>>>>> Stashed changes
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(long id) {
        return studentRepository.findById(id);
    }
}
