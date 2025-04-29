package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.ClassroomDTO;
import co.edu.udes.activity.backend.demo.models.Classroom;
import co.edu.udes.activity.backend.demo.services.ClassroomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @Operation(summary = "Obtener todas las aulas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente")
    })
    @GetMapping
    public List<ClassroomDTO> getAllClassrooms() {
        return classroomService.getAllClassrooms()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener un aula por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aula encontrada"),
        @ApiResponse(responseCode = "404", description = "Aula no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDTO> getClassroomById(@PathVariable Integer id) {
        Optional<Classroom> classroomOpt = classroomService.getClassroomById(id);
        if (classroomOpt.isPresent()) {
            return ResponseEntity.ok(convertToDTO(classroomOpt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Crear un aula")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Aula creada exitosamente")
    })
    @PostMapping
    public ResponseEntity<ClassroomDTO> createClassroom(@Valid @RequestBody ClassroomDTO classroomDTO) {
        Classroom classroom = convertToEntity(classroomDTO);
        Classroom saved = classroomService.saveClassroom(classroom);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(saved));
    }

    @Operation(summary = "Actualizar un aula")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aula actualizada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Aula no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClassroomDTO> updateClassroom(@PathVariable Integer id, @Valid @RequestBody ClassroomDTO classroomDTO) {
        Classroom updated = classroomService.updateClassroom(id, convertToEntity(classroomDTO));
        if (updated != null) {
            return ResponseEntity.ok(convertToDTO(updated));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Eliminar un aula")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aula eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Aula no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClassroom(@PathVariable Integer id) {
        boolean deleted = classroomService.deleteClassroom(id);
        if (deleted) {
            return ResponseEntity.ok("Classroom deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classroom not found with id: " + id);
        }
    }

    // ---------------------------------------
    // Métodos para convertir entre Entity y DTO
    private ClassroomDTO convertToDTO(Classroom classroom) {
        ClassroomDTO dto = new ClassroomDTO();
        dto.setIdClassroom(classroom.getIdClassroom());
        dto.setLocation(classroom.getLocation());
        dto.setCapacity(classroom.getCapacity());
        if (classroom.getClasses() != null) {
            dto.setClassIds(classroom.getClasses().stream()
                    .map(c -> c.getIdClass())
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    private Classroom convertToEntity(ClassroomDTO dto) {
        Classroom classroom = new Classroom();
        classroom.setIdClassroom(dto.getIdClassroom() != null ? dto.getIdClassroom() : 0);
        classroom.setLocation(dto.getLocation());
        classroom.setCapacity(dto.getCapacity());
        // No seteamos clases aquí (solo en controladores avanzados)
        return classroom;
    }
}
