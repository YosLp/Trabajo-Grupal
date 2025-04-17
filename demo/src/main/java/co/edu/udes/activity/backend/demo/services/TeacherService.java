package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.TeacherDTO;
import co.edu.udes.activity.backend.demo.models.Role;
import co.edu.udes.activity.backend.demo.models.Teacher;
import co.edu.udes.activity.backend.demo.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TeacherDTO> getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .map(this::convertToDTO);
    }

    public TeacherDTO saveTeacher(TeacherDTO dto) {
        Teacher teacher = convertToEntity(dto);
        return convertToDTO(teacherRepository.save(teacher));
    }

    public TeacherDTO updateTeacher(Long id, TeacherDTO updatedDTO) {
        return teacherRepository.findById(id).map(teacher -> {
            teacher.setFirstName(updatedDTO.getFirstName());
            teacher.setLastName(updatedDTO.getLastName());
            teacher.setEmail(updatedDTO.getEmail());
            teacher.setStatus(Boolean.valueOf(updatedDTO.getStatus()));
            teacher.setSpecialization(updatedDTO.getSpecialization());

            Role role = new Role();
            role.setName(updatedDTO.getRoleName());
            teacher.setRole(role);

            return convertToDTO(teacherRepository.save(teacher));
        }).orElse(null);
    }

    public boolean deleteTeacher(Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private TeacherDTO convertToDTO(Teacher teacher) {
        TeacherDTO dto = new TeacherDTO();
        dto.setId(teacher.getId());
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());
        dto.setEmail(teacher.getEmail());
        dto.setStatus(String.valueOf(teacher.getStatus()));
        dto.setSpecialization(teacher.getSpecialization());
        dto.setRoleName(teacher.getRole() != null ? teacher.getRole().getName() : null);
        return dto;
    }

    private Teacher convertToEntity(TeacherDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setId(dto.getId());
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setEmail(dto.getEmail());
        teacher.setStatus(Boolean.valueOf(dto.getStatus()));
        teacher.setSpecialization(dto.getSpecialization());

        Role role = new Role();
        role.setName(dto.getRoleName());
        teacher.setRole(role);

        return teacher;
    }
}
