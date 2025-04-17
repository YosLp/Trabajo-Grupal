package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.EnrollmentDTO;
import co.edu.udes.activity.backend.demo.models.Enrollment;
import co.edu.udes.activity.backend.demo.models.Group;
import co.edu.udes.activity.backend.demo.models.Student;
import co.edu.udes.activity.backend.demo.repositories.EnrollmentRepository;
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

    public EnrollmentDTO saveEnrollment(Enrollment enrollment) {
        enrollment.setEnrollmentDate(new Date());
        enrollment.setStatus("Enrolled");
        return convertToDTO(enrollmentRepository.save(enrollment));
    }

    public EnrollmentDTO updateEnrollment(long id, Enrollment updatedEnrollment) {
        return enrollmentRepository.findById(id).map(enrollment -> {
            enrollment.setStudent(updatedEnrollment.getStudent());
            enrollment.setGroup(updatedEnrollment.getGroup());
            enrollment.setEnrollmentDate(updatedEnrollment.getEnrollmentDate());
            enrollment.setStatus(updatedEnrollment.getStatus());
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
