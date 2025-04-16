package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Attendance;
import co.edu.udes.activity.backend.demo.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    public List<Attendance> getAllAttendances() {
        return attendanceService.getAllAttendances();
    }

    @GetMapping("/{id}")
    public Optional<Attendance> getAttendanceById(@PathVariable Integer id) {
        return attendanceService.getAttendanceById(id);
    }

    @PostMapping
    public Attendance createAttendance(@RequestBody Attendance attendance) {
        return attendanceService.saveAttendance(attendance);
    }

    @PutMapping("/{id}")
    public Attendance updateAttendance(@PathVariable Integer id, @RequestBody Attendance updatedAttendance) {
        return attendanceService.updateAttendance(id, updatedAttendance);
    }

    @DeleteMapping("/{id}")
    public String deleteAttendance(@PathVariable Integer id) {
        boolean deleted = attendanceService.deleteAttendance(id);
        return deleted ? "Attendance deleted successfully" : "Attendance not found with id: " + id;
    }
}