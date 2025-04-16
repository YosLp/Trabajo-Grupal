package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Attendance;
import co.edu.udes.activity.backend.demo.repositories.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }

    public Optional<Attendance> getAttendanceById(Integer id) {
        return attendanceRepository.findById(id);
    }

    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public Attendance updateAttendance(Integer id, Attendance updatedAttendance) {
        return attendanceRepository.findById(id).map(att -> {
            att.setLocaldate(updatedAttendance.getLocaldate());
            att.setStatus(updatedAttendance.getStatus());
            att.setSchedule(updatedAttendance.getSchedule());
            return attendanceRepository.save(att);
        }).orElse(null);
    }

    public boolean deleteAttendance(Integer id) {
        if (attendanceRepository.existsById(id)) {
            attendanceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}