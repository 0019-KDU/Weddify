package com.chiradev.weddify.kafka;

import com.chiradev.weddify.entity.NotificationDBIT0019;
import com.chiradev.weddify.service.EmailServiceDBIT0019;
import com.chiradev.weddify.service.NotificationServiceDBIT0019;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationListenerDBIT0019 {
    private final ObjectMapper objectMapper;
    private final EmailServiceDBIT0019 emailService;
    private final NotificationServiceDBIT0019 notificationService;

    @KafkaListener(topics = "notification_topic", groupId = "notification-service-group")
    public void listen(String message) {
        try {
            // Assuming the message is the notification ID
            NotificationDBIT0019 notification = notificationService.getNotificationById(message);
            emailService.sendEmail(notification);
            notificationService.updateNotificationStatus(message, "SENT");
        } catch (Exception e) {
            log.error("Error processing notification: {}", e.getMessage());
            // Optionally, update notification status to FAILED
        }
    }
}
