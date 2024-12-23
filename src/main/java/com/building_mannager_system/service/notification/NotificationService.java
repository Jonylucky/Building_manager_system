package com.building_mannager_system.service.notification;

import com.building_mannager_system.dto.requestDto.NotificationDto;
import com.building_mannager_system.entity.notification.Notification;
import com.building_mannager_system.enums.StatusNotifi;
import com.building_mannager_system.mapper.notification.NotificationMapper;
import com.building_mannager_system.repository.notification.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService {


    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationMapper notificationMapper;
    // Create a new notification
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    // Read a notification by its ID
    public Optional<Notification> getNotificationById(Integer id) {
        return notificationRepository.findById(id);
    }

    // Get all notifications (you can add filters as needed)
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Update a notification
    public Notification updateNotification(Integer id) {
        return notificationRepository.findById(id)
                .map(notification -> {
                    // Set the status to SENT
                    notification.setStatus(StatusNotifi.SENT);
                    // Optionally, update other fields if needed (e.g., set sentAt)
                    notification.setSentAt(LocalDateTime.now()); // Set the current time as sentAt
                    return notificationRepository.save(notification);
                })
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
    }

    // Delete a notification
    public void deleteNotification(Integer id) {
        notificationRepository.deleteById(id);
    }

    public List<NotificationDto> getNotificationsForRecipient(Integer recipientId) {
        // Retrieve notifications from repository
        List<Notification> notifications = notificationRepository.findByRecipient_IdAndStatus(recipientId, StatusNotifi.SENT);

        // Convert to DTOs using MapStruct
        List<NotificationDto> notificationDTOs = notifications.stream()
                .map(notificationMapper::toNotificationDTO)
                .collect(Collectors.toList());
        System.out.println("Number of notifications found: " + notifications.size());
        return notificationDTOs;
    }


}
