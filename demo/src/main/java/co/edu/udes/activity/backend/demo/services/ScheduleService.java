package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.StudentScheduleDTO;
import co.edu.udes.activity.backend.demo.dto.TeacherScheduleDTO;
import co.edu.udes.activity.backend.demo.models.Class;
import co.edu.udes.activity.backend.demo.models.Schedule;
import co.edu.udes.activity.backend.demo.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public List<StudentScheduleDTO> getScheduleForStudent(Long idStudent) {
        List<StudentScheduleDTO> result = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        boolean studentFound = false;

        for (Schedule schedule : scheduleRepository.findAll()) {
            if (schedule.getClasses() != null) {
                for (Class clazz : schedule.getClasses()) {
                    if (clazz.getGroup() != null && clazz.getGroup().getEnrollments() != null) {
                        boolean studentMatches = clazz.getGroup().getEnrollments().stream()
                                .anyMatch(enrollment ->
                                        enrollment.getStudent() != null &&
                                                enrollment.getStudent().getId() == idStudent
                                );

                        if (studentMatches) {
                            studentFound = true;
                            StudentScheduleDTO dto = new StudentScheduleDTO();
                            dto.setClassName(clazz.getDescription());
                            dto.setStartHour(format.format(clazz.getStarHour()));
                            dto.setEndHour(format.format(clazz.getEndHour()));
                            dto.setCourseName(clazz.getGroup().getName());
                            result.add(dto);
                        }
                    }
                }
            }
        }

        if (!studentFound) {
            throw new RuntimeException("El estudiante con ID " + idStudent + " no tiene inscripciones.");
        }
        return result;
    }

    public List<TeacherScheduleDTO> getScheduleForTeacher(Long idTeacher) {
        List<TeacherScheduleDTO> result = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        boolean teacherFound = false;

        for (Schedule schedule : scheduleRepository.findAll()) {
            if (schedule.getClasses() != null) {
                for (Class clazz : schedule.getClasses()) {
                    if (clazz.getGroup() != null &&
                            clazz.getGroup().getTeacher() != null &&
                            clazz.getGroup().getTeacher().getId() == idTeacher) {

                        teacherFound = true;
                        TeacherScheduleDTO dto = new TeacherScheduleDTO();
                        dto.setGroupName(clazz.getGroup().getName());
                        dto.setClassName(clazz.getDescription());
                        dto.setStartHour(format.format(clazz.getStarHour()));
                        dto.setEndHour(format.format(clazz.getEndHour()));
                        result.add(dto);
                    }
                }
            }
        }

        if (!teacherFound) {
            throw new RuntimeException("El profesor con ID " + idTeacher + " no tiene clases asignadas.");
        }
        return result;
    }
}
