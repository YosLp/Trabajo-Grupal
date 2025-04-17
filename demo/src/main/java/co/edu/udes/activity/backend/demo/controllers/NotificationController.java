package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.NotificationDTO;
import co.edu.udes.activity.backend.demo.dto.NotificationRequestDTO;
import co.edu.udes.activity.backend.demo.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<NotificationDTO> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable Long id) {
        NotificationDTO dto = notificationService.getNotificationById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{userId}")
    public ResponseEntity<NotificationDTO> createNotification(@PathVariable Long userId, @RequestBody NotificationRequestDTO dto) {
        NotificationDTO created = notificationService.saveNotification(dto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}/user/{userId}")
    public ResponseEntity<NotificationDTO> updateNotification(
            @PathVariable Long id,
            @PathVariable Long userId,
            @RequestBody NotificationRequestDTO dto) {
        NotificationDTO updated = notificationService.updateNotification(id, dto, userId);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long id) {
        boolean deleted = notificationService.deleteNotification(id);
        return deleted ? ResponseEntity.ok("Notificación eliminada correctamente") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la notificación con ID: " + id);
    }
}

