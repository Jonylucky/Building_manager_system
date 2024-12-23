package com.building_mannager_system.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationDto {
    private Integer id;
    private Integer recipientId; // ID of the recipient
    private String message; // Notification content
    private String status; // Status as a string
    private LocalDateTime createdAt; // Notification creation time
    private LocalDateTime sentAt; // Notification sent time
}
