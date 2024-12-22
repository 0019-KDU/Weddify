package com.chiradev.weddify.controller;

import com.chiradev.weddify.dto.NotificationRequestDBIT0019;
import com.chiradev.weddify.dto.NotificationResponseDBIT0019;
import com.chiradev.weddify.entity.NotificationDBIT0019;
import com.chiradev.weddify.service.EmailServiceDBIT0019;
import com.chiradev.weddify.service.NotificationServiceDBIT0019;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationControllerDBIT0019 {
    private final NotificationServiceDBIT0019 notificationService;
    private final EmailServiceDBIT0019 emailService;
    @PostMapping
    public ResponseEntity<NotificationResponseDBIT0019> createNotification(@Valid @RequestBody NotificationRequestDBIT0019 request) {
        NotificationDBIT0019 notification = NotificationDBIT0019.builder()
                .recipientEmail(request.getRecipientEmail())
                .subject(request.getSubject())
                .message(request.getMessage())
                .build();
        NotificationDBIT0019 savedNotification = notificationService.createNotification(notification);
        NotificationResponseDBIT0019 response = mapToResponse(savedNotification);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponseDBIT0019> getNotificationById(@PathVariable String id) {
        NotificationDBIT0019 notification = notificationService.getNotificationById(id);
        NotificationResponseDBIT0019 response = mapToResponse(notification);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponseDBIT0019>> getNotificationsByStatus(@RequestParam(required = false) String status) {
        List<NotificationDBIT0019> notifications;
        if (status != null) {
            notifications = notificationService.getNotificationsByStatus(status);
        } else {
            notifications = notificationService.getNotificationsByStatus("PENDING");
        }
        List<NotificationResponseDBIT0019> responses = notifications.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/send-email")
    public ResponseEntity<String> sendTestEmail() {
        try {
            // Create a test Notification object
            NotificationDBIT0019 notification = NotificationDBIT0019.builder()
                    .recipientEmail("chirantharavishka@gmail.com")  // Replace with your email
                    .subject("Test Email")
                    .message("This is a test email sent from the Notification Service.")
                    .build();

            // Save the notification to the database
            NotificationDBIT0019 savedNotification = notificationService.createNotification(notification);

            // Send the email using EmailService
            emailService.sendEmail(savedNotification);

            // Update the notification status to SENT
            notificationService.updateNotificationStatus(savedNotification.getId(), "SENT");

            return ResponseEntity.ok("Test email sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send test email: " + e.getMessage());
        }
    }

    private NotificationResponseDBIT0019 mapToResponse(NotificationDBIT0019 notification) {
        return NotificationResponseDBIT0019.builder()
                .id(notification.getId())
                .recipientEmail(notification.getRecipientEmail())
                .subject(notification.getSubject())
                .message(notification.getMessage())
                .status(notification.getStatus())
                .createdAt(notification.getCreatedAt())
                .sentAt(notification.getSentAt())
                .build();
    }
}
