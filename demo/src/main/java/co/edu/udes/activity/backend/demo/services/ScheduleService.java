package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.ScheduleDTO;
import co.edu.udes.activity.backend.demo.dto.StudentScheduleDTO;
import co.edu.udes.activity.backend.demo.dto.TeacherScheduleDTO;
import co.edu.udes.activity.backend.demo.models.Class;
import co.edu.udes.activity.backend.demo.models.Schedule;
import co.edu.udes.activity.backend.demo.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    // — CRUD estándar con DTOs —

    public List<ScheduleDTO> getAllScheduleDTOs() {
        return scheduleRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ScheduleDTO getScheduleDTOById(Integer id) {
        return scheduleRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    public ScheduleDTO saveScheduleDTO(ScheduleDTO dto) {
        Schedule entity = toEntity(dto);
        Schedule saved = scheduleRepository.save(entity);
        return toDTO(saved);
    }

    public ScheduleDTO updateScheduleDTO(Integer id, ScheduleDTO dto) {
        return scheduleRepository.findById(id)
                .map(existing -> {
                    existing.setStarHour(dto.getStarHour());
                    existing.setEndHour(dto.getEndHour());
                    Schedule updated = scheduleRepository.save(existing);
                    return toDTO(updated);
                })
                .orElse(null);
    }

    public boolean deleteSchedule(Integer id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // — Endpoints específicos de negocio —

    public List<StudentScheduleDTO> getScheduleForStudent(Long idStudent) {
        List<StudentScheduleDTO> result = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        boolean found = false;

        for (Schedule schedule : scheduleRepository.findAll()) {
            if (schedule.getClasses() == null) continue;

            for (Class clazz : schedule.getClasses()) {
                if (clazz.getGroup() == null || clazz.getGroup().getEnrollments() == null) continue;

                boolean matches = clazz.getGroup().getEnrollments().stream()
                        .anyMatch(e -> e.getStudent() != null && e.getStudent().getId() == idStudent);

                if (matches) {
                    found = true;
                    StudentScheduleDTO dto = new StudentScheduleDTO();
                    dto.setClassName(clazz.getDescription());
                    dto.setStartHour(fmt.format(clazz.getStarHour()));
                    dto.setEndHour(fmt.format(clazz.getEndHour()));
                    dto.setCourseName(clazz.getGroup().getCourse().getName());
                    result.add(dto);
                }
            }
        }
        if (!found) {
            throw new RuntimeException("El estudiante con ID " + idStudent + " no tiene inscripciones.");
        }
        return result;
    }

    public List<TeacherScheduleDTO> getScheduleForTeacher(Long idTeacher) {
        List<TeacherScheduleDTO> result = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        boolean found = false;

        for (Schedule schedule : scheduleRepository.findAll()) {
            if (schedule.getClasses() == null) continue;

            for (Class clazz : schedule.getClasses()) {
                if (clazz.getGroup() == null || clazz.getGroup().getTeacher() == null) continue;

                if (clazz.getGroup().getTeacher().getId() == idTeacher) {
                    found = true;
                    TeacherScheduleDTO dto = new TeacherScheduleDTO();
                    dto.setGroupName(clazz.getGroup().getCourse().getName());
                    dto.setClassName(clazz.getDescription());
                    dto.setStartHour(fmt.format(clazz.getStarHour()));
                    dto.setEndHour(fmt.format(clazz.getEndHour()));
                    result.add(dto);
                }
            }
        }
        if (!found) {
            throw new RuntimeException("El profesor con ID " + idTeacher + " no tiene clases asignadas.");
        }
        return result;
    }

    // — Conversores Entity ↔ DTO —

    private ScheduleDTO toDTO(Schedule s) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setIdSchedule(s.getIdSchedule());
        dto.setStarHour(s.getStarHour());
        dto.setEndHour(s.getEndHour());
        if (s.getClasses() != null) {
            dto.setClassIds(
                    s.getClasses()
                     .stream()
                     .map(Class::getIdClass)
                     .collect(Collectors.toList())
            );
        }
        return dto;
    }

    private Schedule toEntity(ScheduleDTO dto) {
        Schedule s = new Schedule();
        s.setStarHour(dto.getStarHour());
        s.setEndHour(dto.getEndHour());
        // No seteamos las clases aquí
        return s;
    }
}
