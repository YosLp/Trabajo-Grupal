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

    public Optional<TeacherDTO> getTeacherByDocumentNumber(String documentNumber) {
        return teacherRepository.findByDocumentNumber(documentNumber)
                .map(this::convertToDTO);
    }

    public Optional<TeacherDTO> getTeacherByInstitutionalCode(String institutionalCode) {
        return teacherRepository.findByInstitutionalCode(institutionalCode)
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
        dto.setStatus(teacher.getStatus());
        dto.setRoleName(teacher.getRole() != null ? teacher.getRole().getName() : null);
        dto.setSpecialization(teacher.getSpecialization());
        dto.setPassword(teacher.getPassword());
        dto.setDocumentNumber(teacher.getDocumentNumber());
        dto.setInstitutionalCode(teacher.getInstitutionalCode());
        dto.setStatusContract(teacher.getStatusContract());
        return dto;
    }

    private Teacher convertToEntity(TeacherDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setId(dto.getId());
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setEmail(dto.getEmail());
        teacher.setStatus(dto.getStatus());
        teacher.setSpecialization(dto.getSpecialization());
        teacher.setPassword(dto.getPassword());
        teacher.setDocumentNumber(dto.getDocumentNumber());
        teacher.setInstitutionalCode(dto.getInstitutionalCode());
        teacher.setStatusContract(dto.getStatusContract());
        return teacher;
    }
}
