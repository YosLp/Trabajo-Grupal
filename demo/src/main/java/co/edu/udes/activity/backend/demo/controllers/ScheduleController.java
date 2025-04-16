package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Schedule;
import co.edu.udes.activity.backend.demo.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/{id}")
    public Optional<Schedule> getScheduleById(@PathVariable Integer id) {
        return scheduleService.getScheduleById(id);
    }

    @PostMapping
    public Schedule createSchedule(@RequestBody Schedule schedule) {
        return scheduleService.saveSchedule(schedule);
    }

    @PutMapping("/{id}")
    public Schedule updateSchedule(@PathVariable Integer id, @RequestBody Schedule updatedSchedule) {
        return scheduleService.updateSchedule(id, updatedSchedule);
    }

    @DeleteMapping("/{id}")
    public String deleteSchedule(@PathVariable Integer id) {
        boolean deleted = scheduleService.deleteSchedule(id);
        return deleted ? "Schedule deleted successfully" : "Schedule not found with id: " + id;
    }
}