package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.EnrollmentDTO;
import co.edu.udes.activity.backend.demo.dto.EnrollmentRequestDTO;
import co.edu.udes.activity.backend.demo.models.Enrollment;
import co.edu.udes.activity.backend.demo.models.Group;
import co.edu.udes.activity.backend.demo.models.Student;
import co.edu.udes.activity.backend.demo.repositories.EnrollmentRepository;
import co.edu.udes.activity.backend.demo.repositories.GroupRepository;
import co.edu.udes.activity.backend.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public EnrollmentDTO getEnrollmentById(Long id) {
        Optional<Enrollment> enrollmentOpt = enrollmentRepository.findById(id);
        return enrollmentOpt.map(this::mapToDTO).orElse(null);
    }

    public EnrollmentDTO createEnrollment(EnrollmentRequestDTO dto) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(studentRepository.findById(dto.getStudentId()).orElseThrow());
        enrollment.setGroup(groupRepository.findById(dto.getGroupId()).orElseThrow());
        enrollment.setEnrollmentDate(dto.getEnrollmentDate());
        enrollment.setStatus(dto.getStatus());
        enrollment.setQualification(dto.getQualification());
        enrollment.setP1Qualification(dto.getP1Qualification());
        enrollment.setP2Qualification(dto.getP2Qualification());
        enrollment.setP3Qualification(dto.getP3Qualification());

        Enrollment saved = enrollmentRepository.save(enrollment);
        return mapToDTO(saved);
    }

    public EnrollmentDTO updateEnrollment(Long id, EnrollmentRequestDTO dto) {
        Optional<Enrollment> enrollmentOpt = enrollmentRepository.findById(id);
        if (enrollmentOpt.isPresent()) {
            Enrollment enrollment = enrollmentOpt.get();
            enrollment.setStudent(studentRepository.findById(dto.getStudentId()).orElseThrow());
            enrollment.setGroup(groupRepository.findById(dto.getGroupId()).orElseThrow());
            enrollment.setEnrollmentDate(dto.getEnrollmentDate());
            enrollment.setStatus(dto.getStatus());
            enrollment.setQualification(dto.getQualification());
            enrollment.setP1Qualification(dto.getP1Qualification());
            enrollment.setP2Qualification(dto.getP2Qualification());
            enrollment.setP3Qualification(dto.getP3Qualification());

            Enrollment updated = enrollmentRepository.save(enrollment);
            return mapToDTO(updated);
        } else {
            return null;
        }
    }

    public boolean deleteEnrollment(Long id) {
        if (enrollmentRepository.existsById(id)) {
            enrollmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Enrollment> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    private EnrollmentDTO mapToDTO(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setIdEnrollment(enrollment.getIdEnrollment());
        dto.setStudentId(enrollment.getStudent().getId());
        dto.setGroupId(enrollment.getGroup().getId());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());
        dto.setStatus(enrollment.getStatus());
        dto.setQualification(enrollment.getQualification());
        dto.setP1Qualification(enrollment.getP1Qualification());
        dto.setP2Qualification(enrollment.getP2Qualification());
        dto.setP3Qualification(enrollment.getP3Qualification());
        return dto;
    }
}
