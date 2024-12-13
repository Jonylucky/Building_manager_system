package com.building_mannager_system.dto.requestDto.verificationDto;

import com.building_mannager_system.enums.StatusNotifi;

import java.time.LocalDateTime;

public class NotificationMaintenanceDto {
    private Long id;
    private String title;
    private String message;
    private String recipient;
    private StatusNotifi status; // String để dễ dàng hiển thị trong UI
    private LocalDateTime createdAt;
    private LocalDateTime maintenanceDate;
}
