package co.edu.udes.activity.backend.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.udes.activity.backend.demo.models.Notification;
import co.edu.udes.activity.backend.demo.repositories.NotificationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications(){
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(Long id){
        return notificationRepository.findById(id);
    }

    public Notification saveNotification(Notification notification){
        return notificationRepository.save(notification);

    }

    public Notification updateNotification(Long id, Notification updatedNotification) {
        return notificationRepository.findById(id).map(notification -> {
            notification.setSendDate(updatedNotification.getSendDate());
            notification.setRead(updatedNotification.isRead());
            notification.setMessageType(updatedNotification.getMessageType());
            notification.setMessaging(updatedNotification.getMessaging());
            notification.setReceiver(updatedNotification.getReceiver());
            return notificationRepository.save(notification);
        }).orElse(null);
    }

    public boolean deleteNotification(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
