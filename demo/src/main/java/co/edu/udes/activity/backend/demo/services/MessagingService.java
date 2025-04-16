package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Messaging;
import co.edu.udes.activity.backend.demo.models.User;
import co.edu.udes.activity.backend.demo.repositories.MessagingRepository;
import co.edu.udes.activity.backend.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class MessagingService {

    @Autowired
    private UserRepository userRepository;

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

    public void sendMessage(Long idRecipient, Long idSender, String content, String messageType) {
        Optional<User> recipient = userRepository.findById(idRecipient);
        Optional<User> sender = userRepository.findById(idSender);

        if (recipient.isPresent() && sender.isPresent()) {
            Messaging message = new Messaging();
            message.setSender(sender.get());
            message.setReceiver(recipient.get());
            message.setContent(content);
            message.setSendDate(LocalDateTime.now());
            message.setRead(false);
            message.setMessageType(messageType);
            messagingRepository.save(message);
        }
    }

    public void markAsRead(Long messageId) {
        Optional<Messaging> optionalMessage = messagingRepository.findById(messageId);
        optionalMessage.ifPresent(message -> {
            message.setRead(true);
            messagingRepository.save(message);
        });
    }

    public List<Messaging> getMessagesByUser(Long userId) {
        return messagingRepository.findByReceiverId(userId);
    }
}