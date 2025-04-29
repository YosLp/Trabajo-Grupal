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

import java.text.SimpleDateFormat;
import java.util.Date;
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
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EnrollmentDTO getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public EnrollmentDTO createEnrollment(EnrollmentRequestDTO req) {
        Student student = studentRepository.findById(req.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found: " + req.getStudentId()));
        Group group = groupRepository.findById(req.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found: " + req.getGroupId()));

        Enrollment en = new Enrollment();
        en.setStudent(student);
        en.setGroup(group);
        en.setEnrollmentDate(req.getEnrollmentDate());
        en.setStatus(req.getStatus());
        en.setQualification(req.getQualification());
        en.setP1_qualification(req.getP1Qualification());
        en.setP2_qualification(req.getP2Qualification());
        en.setP3_qualification(req.getP3Qualification());

        return convertToDTO(enrollmentRepository.save(en));
    }

    public EnrollmentDTO updateEnrollment(Long id, EnrollmentRequestDTO req) {
        Optional<Enrollment> opt = enrollmentRepository.findById(id);
        if (opt.isPresent()) {
            Enrollment en = opt.get();
            studentRepository.findById(req.getStudentId()).ifPresent(en::setStudent);
            groupRepository.findById(req.getGroupId()).ifPresent(en::setGroup);
            en.setEnrollmentDate(req.getEnrollmentDate());
            en.setStatus(req.getStatus());
            en.setQualification(req.getQualification());
            en.setP1_qualification(req.getP1Qualification());
            en.setP2_qualification(req.getP2Qualification());
            en.setP3_qualification(req.getP3Qualification());
            return convertToDTO(enrollmentRepository.save(en));
        }
        return null;
    }

    public boolean deleteEnrollment(Long id) {
        if (enrollmentRepository.existsById(id)) {
            enrollmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private EnrollmentDTO convertToDTO(Enrollment en) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setIdEnrollment(en.getIdEnrollment());
        dto.setStudentId(en.getStudent().getId());
        dto.setGroupId(en.getGroup().getId());
        dto.setEnrollmentDate(en.getEnrollmentDate());
        dto.setStatus(en.getStatus());
        dto.setQualification(en.getQualification());
        dto.setP1Qualification(en.getP1_qualification());
        dto.setP2Qualification(en.getP2_qualification());
        dto.setP3Qualification(en.getP3_qualification());
        return dto;
    }

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }
}