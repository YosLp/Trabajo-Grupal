package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.NotificationDTO;
import co.edu.udes.activity.backend.demo.dto.NotificationRequestDTO;
import co.edu.udes.activity.backend.demo.models.Messaging;
import co.edu.udes.activity.backend.demo.models.User;
import co.edu.udes.activity.backend.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.udes.activity.backend.demo.models.Notification;
import co.edu.udes.activity.backend.demo.repositories.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<NotificationDTO> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(notification -> modelMapper.map(notification, NotificationDTO.class))
                .collect(Collectors.toList());
    }

    public NotificationDTO getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .map(notification -> modelMapper.map(notification, NotificationDTO.class))
                .orElse(null);
    }

    public NotificationDTO saveNotification(NotificationRequestDTO dto, Long userId) {
        Notification notification = modelMapper.map(dto, Notification.class);

        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(notification::setReceiver);

        notification.setRead(false); // Por defecto al crear
        return modelMapper.map(notificationRepository.save(notification), NotificationDTO.class);
    }

    public NotificationDTO updateNotification(Long id, NotificationRequestDTO updatedDTO, Long userId) {
        return notificationRepository.findById(id).map(notification -> {
            notification.setSendDate(updatedDTO.getSendDate());
            notification.setMessageType(updatedDTO.getMessageType());
            notification.setMessaging(updatedDTO.getMessaging());
            notification.setRead(true); // Al actualizar podría marcarse como leída

            if (userId != null) {
                userRepository.findById(userId).ifPresent(notification::setReceiver);
            }

            return modelMapper.map(notificationRepository.save(notification), NotificationDTO.class);
        }).orElse(null);
    }

    public boolean deleteNotification(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void sendNotification(Long recipientId, Messaging message, String type) {
        Optional<User> recipient = userRepository.findById(recipientId);

        if (recipient.isPresent()) {
            Notification notification = new Notification();
            notification.setReceiver(recipient.get());
            notification.setMessaging(message);
            notification.setSendDate(LocalDateTime.now());
            notification.setRead(false);
            notification.setMessageType(type);
            notificationRepository.save(notification);
        }
    }
}

