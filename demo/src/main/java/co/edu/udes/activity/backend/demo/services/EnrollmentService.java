package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Enrollment;
import co.edu.udes.activity.backend.demo.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Optional<Enrollment> getEnrollmentById(long id) {
        return enrollmentRepository.findById(id);
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment updateEnrollment(long id, Enrollment updatedEnrollment) {
        return enrollmentRepository.findById(id).map(enrollment -> {
            enrollment.setStudent(updatedEnrollment.getStudent());
            enrollment.setGroup(updatedEnrollment.getGroup());
            return enrollmentRepository.save(enrollment);
        }).orElse(null);
    }

    public boolean deleteEnrollment(long id) {
        if (enrollmentRepository.existsById( id)) {
            enrollmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
