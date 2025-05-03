package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.EnrollmentDTO;
import co.edu.udes.activity.backend.demo.dto.EnrollmentCreateDTO;
import co.edu.udes.activity.backend.demo.dto.EnrollmentRequestDTO;
import co.edu.udes.activity.backend.demo.models.Cut;
import co.edu.udes.activity.backend.demo.models.Enrollment;
import co.edu.udes.activity.backend.demo.models.Group;
import co.edu.udes.activity.backend.demo.models.Student;
import co.edu.udes.activity.backend.demo.repositories.EnrollmentRepository;
import co.edu.udes.activity.backend.demo.repositories.GroupRepository;
import co.edu.udes.activity.backend.demo.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EnrollmentDTO getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public EnrollmentDTO createEnrollment(EnrollmentCreateDTO dto) {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentDate(dto.getEnrollmentDate());
        enrollment.setStatus(dto.getStatus());

        studentRepository.findById(dto.getStudentId()).ifPresent(enrollment::setStudent);
        groupRepository.findById(dto.getGroupId()).ifPresent(enrollment::setGroup);

        Enrollment saved = enrollmentRepository.save(enrollment);
        return convertToDTO(saved);
    }

    public EnrollmentDTO updateEnrollment(Long id, EnrollmentRequestDTO dto) {
        return enrollmentRepository.findById(id).map(enrollment -> {
            enrollment.setEnrollmentDate(dto.getEnrollmentDate());
            enrollment.setStatus(dto.getStatus());
            enrollment.setFinalQualification(dto.getQualification());

            studentRepository.findById(dto.getStudentId()).ifPresent(enrollment::setStudent);
            groupRepository.findById(dto.getGroupId()).ifPresent(enrollment::setGroup);

            return convertToDTO(enrollmentRepository.save(enrollment));
        }).orElse(null);
    }

    public boolean deleteEnrollment(Long id) {
        if (enrollmentRepository.existsById(id)) {
            enrollmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private EnrollmentDTO convertToDTO(Enrollment enrollment) {
        EnrollmentDTO dto = modelMapper.map(enrollment, EnrollmentDTO.class);
        dto.setStudentId(enrollment.getStudent().getId());
        dto.setGroupId(enrollment.getGroup().getId());


        List<Long> cutIds = enrollment.getCuts().stream()
                .map(Cut::getId)
                .collect(Collectors.toList());
        dto.setCutIds(cutIds);

        return dto;
    }
}
