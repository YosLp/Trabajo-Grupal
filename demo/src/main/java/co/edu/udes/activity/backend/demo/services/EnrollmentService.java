package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Enrollment;
import co.edu.udes.activity.backend.demo.repository.EnrollmentRepository;

import co.edu.udes.activity.backend.demo.models.Group;
import co.edu.udes.activity.backend.demo.models.Student;
import co.edu.udes.activity.backend.demo.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    public Optional<Enrollment> getEnrollmentById(int id) {
    public Optional<Enrollment> getEnrollmentById(Long id) {
    public Optional<Enrollment> getEnrollmentById(Long id) {
    public Optional<Enrollment> getEnrollmentById(long id) {
        return enrollmentRepository.findById(id);
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        enrollment.setEnrollmentDate(new Date());
        enrollment.setStatus("Enrolled");
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment updateEnrollment(int id, Enrollment updatedEnrollment) {
    public Enrollment updateEnrollment(Long id, Enrollment updatedEnrollment) {
    public Enrollment updateEnrollment(Long id, Enrollment updatedEnrollment) {
        return enrollmentRepository.findById(id).map(enrollment -> {
            enrollment.setStudent(updatedEnrollment.getStudent());
            enrollment.setGroup(updatedEnrollment.getGroup());
            enrollment.setEnrollmentDate(updatedEnrollment.getEnrollmentDate());
            enrollment.setStatus(updatedEnrollment.getStatus());
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment updateEnrollment(long id, Enrollment updatedEnrollment) {
        return enrollmentRepository.findById(id).map(enrollment -> {
            enrollment.setStudent(updatedEnrollment.getStudent());
            enrollment.setGroup(updatedEnrollment.getGroup());
            return enrollmentRepository.save(enrollment);
        }).orElse(null);
    }

    public boolean deleteEnrollment(int id) {
    public boolean deleteEnrollment(Long id) {
    public boolean deleteEnrollment(Long id) {
        if (enrollmentRepository.existsById(id)) {
    public boolean deleteEnrollment(long id) {
        if (enrollmentRepository.existsById( id)) {
            enrollmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Enrollment enrollStudent(Student student, Group group) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setGroup(group);
        enrollment.setEnrollmentDate(new Date());
        enrollment.setStatus("Enrolled");
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment cancelEnrollment(Long enrollmentId) {
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(enrollmentId);
        if (optionalEnrollment.isPresent()) {
            Enrollment enrollment = optionalEnrollment.get();
            enrollment.setStatus("Cancelled");
            return enrollmentRepository.save(enrollment);
        }
        return null;
    }
}


=======
}
