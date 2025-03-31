package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Messaging;
import co.edu.udes.activity.backend.demo.repositories.MessagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class MessagingService {

    @Autowired
    private MessagingRepository messagingRepository;

    public List<Messaging> getAllMessages() {
        return messagingRepository.findAll();
    }

    public Optional<Messaging> getMessageById(Long id) {
        return messagingRepository.findById(id);
    }

    public Messaging saveMessage(Messaging message) {
        return messagingRepository.save(message);
    }

    public Messaging updateMessage(Long id, Messaging updatedMessage) {
        return messagingRepository.findById(id).map(message -> {
            message.setContent(updatedMessage.getContent());
            message.setSendDate(updatedMessage.getSendDate());
            message.setRead(updatedMessage.isRead());
            message.setMessageType(updatedMessage.getMessageType());
            message.setSender(updatedMessage.getSender());
            message.setReceiver(updatedMessage.getReceiver());
            return messagingRepository.save(message);
        }).orElse(null);
    }

    public boolean deleteMessage(Long id) {
        if (messagingRepository.existsById(id)) {
            messagingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}