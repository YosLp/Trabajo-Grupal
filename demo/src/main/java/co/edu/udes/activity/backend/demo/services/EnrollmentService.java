package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.EnrollmentDTO;
import co.edu.udes.activity.backend.demo.dto.EnrollmentCreateDTO;
import co.edu.udes.activity.backend.demo.models.Enrollment;
import co.edu.udes.activity.backend.demo.models.Group;
import co.edu.udes.activity.backend.demo.models.Student;
import co.edu.udes.activity.backend.demo.repositories.EnrollmentRepository;
import co.edu.udes.activity.backend.demo.repositories.GroupRepository;
import co.edu.udes.activity.backend.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    private EnrollmentDTO convertToDTO(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(enrollment.getIdEnrollment());
        dto.setStudentName(enrollment.getStudent().getFirstName() + " " + enrollment.getStudent().getLastName());
        dto.setGroupName(enrollment.getGroup().getName());
        dto.setEnrollmentDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(enrollment.getEnrollmentDate()));
        dto.setStatus(enrollment.getStatus());
        return dto;
    }

    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EnrollmentDTO> getEnrollmentById(long id) {
        return enrollmentRepository.findById(id).map(this::convertToDTO);
    }

    public EnrollmentDTO saveEnrollment(EnrollmentCreateDTO dto) {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentDate(dto.getEnrollmentDate());
        enrollment.setStatus(dto.getStatus());

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + dto.getStudentId()));
        Group group = groupRepository.findById((long) dto.getGroupId())
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado con ID: " + dto.getGroupId()));

        enrollment.setStudent(student);
        enrollment.setGroup(group);

        return convertToDTO(enrollmentRepository.save(enrollment));
    }

    public EnrollmentDTO updateEnrollment(long id, EnrollmentCreateDTO updatedDTO) {
        return enrollmentRepository.findById(id).map(enrollment -> {
            Student student = studentRepository.findById(updatedDTO.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + updatedDTO.getStudentId()));
            Group group = groupRepository.findById((long) updatedDTO.getGroupId())
                    .orElseThrow(() -> new RuntimeException("Grupo no encontrado con ID: " + updatedDTO.getGroupId()));

            enrollment.setStudent(student);
            enrollment.setGroup(group);
            enrollment.setEnrollmentDate(updatedDTO.getEnrollmentDate());
            enrollment.setStatus(updatedDTO.getStatus());

            return convertToDTO(enrollmentRepository.save(enrollment));
        }).orElse(null);
    }

    public boolean deleteEnrollment(long id) {
        if (enrollmentRepository.existsById(id)) {
            enrollmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
