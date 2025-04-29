package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.ScheduleDTO;
import co.edu.udes.activity.backend.demo.dto.StudentScheduleDTO;
import co.edu.udes.activity.backend.demo.dto.TeacherScheduleDTO;
import co.edu.udes.activity.backend.demo.services.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Operation(summary = "Obtener todos los horarios")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado de horarios obtenido")
    })
    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllScheduleDTOs());
    }

    @Operation(summary = "Obtener un horario por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Horario encontrado"),
        @ApiResponse(responseCode = "404", description = "Horario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable Integer id) {
        ScheduleDTO dto = scheduleService.getScheduleDTOById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Crear un nuevo horario")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Horario creado exitosamente")
    })
    @PostMapping
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleDTO dto) {
        ScheduleDTO created = scheduleService.saveScheduleDTO(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Actualizar un horario existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Horario actualizado"),
        @ApiResponse(responseCode = "404", description = "Horario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDTO> updateSchedule(
            @PathVariable Integer id,
            @RequestBody ScheduleDTO dto) {

        ScheduleDTO updated = scheduleService.updateScheduleDTO(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Eliminar un horario")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Horario eliminado"),
        @ApiResponse(responseCode = "404", description = "Horario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Integer id) {
        boolean deleted = scheduleService.deleteSchedule(id);
        if (deleted) {
            return ResponseEntity.ok("Horario eliminado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horario no encontrado con ID: " + id);
    }

    @Operation(summary = "Obtener horario de un estudiante")
    @GetMapping("/student/{idStudent}")
    public ResponseEntity<List<StudentScheduleDTO>> getScheduleForStudent(@PathVariable Long idStudent) {
        try {
            return ResponseEntity.ok(scheduleService.getScheduleForStudent(idStudent));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(List.of());
        }
    }

    @Operation(summary = "Obtener horario de un profesor")
    @GetMapping("/teacher/{idTeacher}")
    public ResponseEntity<List<TeacherScheduleDTO>> getScheduleForTeacher(@PathVariable Long idTeacher) {
        try {
            return ResponseEntity.ok(scheduleService.getScheduleForTeacher(idTeacher));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(List.of());
        }
    }
}
