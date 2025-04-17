package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.StudentDTO;
import co.edu.udes.activity.backend.demo.models.Role;
import co.edu.udes.activity.backend.demo.models.Student;
import co.edu.udes.activity.backend.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<StudentDTO> getStudentById(long id) {
        return studentRepository.findById(id).map(this::convertToDTO);
    }

    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        return convertToDTO(studentRepository.save(student));
    }

    public StudentDTO updateStudent(long id, StudentDTO updatedDTO) {
        return studentRepository.findById(id).map(student -> {
            student.setFirstName(updatedDTO.getFirstName());
            student.setLastName(updatedDTO.getLastName());
            student.setEmail(updatedDTO.getEmail());
            student.setStatus(Boolean.valueOf(updatedDTO.getStatus()));
            student.setPhoneNumber(updatedDTO.getPhoneNumber());
            student.setAddress(updatedDTO.getAddress());
            student.setRegistrationDate(updatedDTO.getRegistrationDate());
            student.setStatusStudent(updatedDTO.getStatusStudent());

            Role role = new Role();  // simple setter for now
            role.setName(updatedDTO.getRoleName());
            student.setRole(role);

            return convertToDTO(studentRepository.save(student));
        }).orElse(null);
    }

    public boolean deleteStudent(long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private StudentDTO convertToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setStatus(String.valueOf(student.getStatus()));
        dto.setRoleName(student.getRole() != null ? student.getRole().getName() : null);

        dto.setAddress(student.getAddress());
        dto.setStatusStudent(student.getStatusStudent());
        dto.setRegistrationDate(student.getRegistrationDate());
        dto.setPhoneNumber(student.getPhoneNumber());

        return dto;
    }

    private Student convertToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setStatus(Boolean.valueOf(dto.getStatus()));
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setAddress(dto.getAddress());
        student.setRegistrationDate(dto.getRegistrationDate());
        student.setStatusStudent(dto.getStatusStudent());

        Role role = new Role();
        role.setName(dto.getRoleName());
        student.setRole(role);

        return student;
    }
}
