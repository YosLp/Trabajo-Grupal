package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.MessagingDTO;
import co.edu.udes.activity.backend.demo.dto.MessagingRequestDTO;
import co.edu.udes.activity.backend.demo.models.Messaging;
import co.edu.udes.activity.backend.demo.models.User;
import co.edu.udes.activity.backend.demo.repositories.MessagingRepository;
import co.edu.udes.activity.backend.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessagingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessagingRepository messagingRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<MessagingDTO> getAllMessages() {
        return messagingRepository.findAll()
                .stream()
                .map(message -> modelMapper.map(message, MessagingDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<MessagingDTO> getMessageById(Long id) {
        return messagingRepository.findById(id)
                .map(message -> modelMapper.map(message, MessagingDTO.class));
    }

    public MessagingDTO saveMessage(MessagingRequestDTO request) {
        Messaging message = new Messaging();
        message.setContent(request.getContent());
        message.setSendDate(request.getSendDate());
        message.setMessageType(request.getMessageType());
        message.setRead(false); // Por defecto es falso

        userRepository.findById(request.getSenderId()).ifPresent(message::setSender);
        userRepository.findById(request.getReceiverId()).ifPresent(message::setReceiver);

        return modelMapper.map(messagingRepository.save(message), MessagingDTO.class);
    }

    public MessagingDTO updateMessage(Long id, MessagingRequestDTO request) {
        return messagingRepository.findById(id).map(message -> {
            message.setContent(request.getContent());
            message.setSendDate(request.getSendDate());
            message.setMessageType(request.getMessageType());
            userRepository.findById(request.getSenderId()).ifPresent(message::setSender);
            userRepository.findById(request.getReceiverId()).ifPresent(message::setReceiver);
            return modelMapper.map(messagingRepository.save(message), MessagingDTO.class);
        }).orElse(null);
    }

    public boolean deleteMessage(Long id) {
        if (messagingRepository.existsById(id)) {
            messagingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public MessagingDTO sendMessage(Long idRecipient, Long idSender, String content, String messageType) {
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
            return modelMapper.map(messagingRepository.save(message), MessagingDTO.class);
        }
        return null;
    }

    public boolean markAsRead(Long messageId) {
        Optional<Messaging> optionalMessage = messagingRepository.findById(messageId);
        if (optionalMessage.isPresent()) {
            Messaging message = optionalMessage.get();
            message.setRead(true);
            messagingRepository.save(message);
            return true;
        }
        return false;
    }

    public List<MessagingDTO> getMessagesByUser(Long userId) {
        return messagingRepository.findByReceiverId(userId)
                .stream()
                .map(message -> modelMapper.map(message, MessagingDTO.class))
                .collect(Collectors.toList());
    }
}

