package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.MessagingDTO;
import co.edu.udes.activity.backend.demo.dto.MessagingRequestDTO;
import co.edu.udes.activity.backend.demo.models.Messaging;
import co.edu.udes.activity.backend.demo.services.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessagingController {

    @Autowired
    private MessagingService messagingService;

    @GetMapping("/all")
    public List<MessagingDTO> getAllMessages() {
        return messagingService.getAllMessages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessagingDTO> getMessageById(@PathVariable Long id) {
        return messagingService.getMessageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MessagingDTO> createMessage(@RequestBody MessagingRequestDTO dto) {
        return ResponseEntity.ok(messagingService.saveMessage(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessagingDTO> updateMessage(@PathVariable Long id, @RequestBody MessagingRequestDTO dto) {
        MessagingDTO updated = messagingService.updateMessage(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long id) {
        boolean deleted = messagingService.deleteMessage(id);
        return deleted ?
                ResponseEntity.ok("Mensaje eliminado correctamente") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el mensaje con ID: " + id);
    }

    @PostMapping("/send")
    public ResponseEntity<MessagingDTO> sendMessage(@RequestParam Long senderId,
                                                    @RequestParam Long recipientId,
                                                    @RequestParam String content,
                                                    @RequestParam String messageType) {
        MessagingDTO message = messagingService.sendMessage(recipientId, senderId, content, messageType);
        return message != null ? ResponseEntity.ok(message) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/read/{id}")
    public ResponseEntity<String> markAsRead(@PathVariable Long id) {
        boolean updated = messagingService.markAsRead(id);
        return updated ?
                ResponseEntity.ok("Mensaje marcado como leído.") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mensaje no encontrado.");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MessagingDTO>> getMessages(@PathVariable Long userId) {
        return ResponseEntity.ok(messagingService.getMessagesByUser(userId));
    }
}

