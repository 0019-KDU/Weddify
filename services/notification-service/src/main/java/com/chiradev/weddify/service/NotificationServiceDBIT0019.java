package com.chiradev.weddify.service;

import com.chiradev.weddify.entity.NotificationDBIT0019;
import com.chiradev.weddify.exception.NotificationNotFoundException;
import com.chiradev.weddify.repository.NotificationRepositoryDBIT0019;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceDBIT0019 {
    private final NotificationRepositoryDBIT0019 notificationRepository;
    private final EmailServiceDBIT0019 emailService;

    public NotificationDBIT0019 createNotification(NotificationDBIT0019 notification) {
        notification.setStatus("PENDING");
        notification.setCreatedAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    public List<NotificationDBIT0019> getNotificationsByStatus(String status) {
        return notificationRepository.findByStatus(status);
    }

    public NotificationDBIT0019 getNotificationById(String id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found with id: " + id));
    }

    public void updateNotificationStatus(String id, String status) {
        NotificationDBIT0019 notification = getNotificationById(id);
        notification.setStatus(status);
        if (status.equals("SENT")) {
            notification.setSentAt(LocalDateTime.now());
        }
        notificationRepository.save(notification);
    }
}
