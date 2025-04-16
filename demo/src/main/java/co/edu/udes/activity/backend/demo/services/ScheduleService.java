package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Schedule;
import co.edu.udes.activity.backend.demo.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Optional<Schedule> getScheduleById(Integer id) {
        return scheduleRepository.findById(id);
    }

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule updateSchedule(Integer id, Schedule updatedSchedule) {
        return scheduleRepository.findById(id).map(schedule -> {
            schedule.setStarHour(updatedSchedule.getStarHour());
            schedule.setEndHour(updatedSchedule.getEndHour());
            // Agrega otras propiedades si fuese necesario
            return scheduleRepository.save(schedule);
        }).orElse(null);
    }

    public boolean deleteSchedule(Integer id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}