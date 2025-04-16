package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Messaging;
import co.edu.udes.activity.backend.demo.services.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessagingController {
    
    @Autowired
    private MessagingService messagingService;

    @GetMapping
    public List<Messaging> getAllMessages() {
        return messagingService.getAllMessages();
    }

    @GetMapping("/{id}")
    public Optional<Messaging> getMessageById(@PathVariable Long id) {
        return messagingService.getMessageById(id);
    }

    @PostMapping
    public Messaging createMessage(@RequestBody Messaging message) {
        return messagingService.saveMessage(message);
    }

    @PutMapping("/{id}")
    public Messaging updateMessage(@PathVariable Long id, @RequestBody Messaging updatedMessage) {
        return messagingService.updateMessage(id, updatedMessage);
    }

    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable Long id) {
        boolean deleted = messagingService.deleteMessage(id);
        return deleted ? "Mensaje eliminado correctamente" : "No se encontró el mensaje con ID: " + id;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam Long senderId,
                                              @RequestParam Long recipientId,
                                              @RequestParam String content,
                                              @RequestParam String messageType) {
        messagingService.sendMessage(recipientId, senderId, content, messageType);
        return ResponseEntity.ok("Mensaje enviado exitosamente.");
    }

    @PutMapping("/read/{id}")
    public ResponseEntity<String> markAsRead(@PathVariable Long id) {
        messagingService.markAsRead(id);
        return ResponseEntity.ok("Mensaje marcado como leído.");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Messaging>> getMessages(@PathVariable Long userId) {
        return ResponseEntity.ok(messagingService.getMessagesByUser(userId));
    }
}
