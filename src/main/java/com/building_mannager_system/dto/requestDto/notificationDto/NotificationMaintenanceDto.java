package com.building_mannager_system.dto.requestDto.notificationDto;

import com.building_mannager_system.enums.StatusNotifi;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationMaintenanceDto {
    private Long id;
    private String title;
    private String message;
    private String recipient;
    private StatusNotifi status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")// String để dễ dàng hiển thị trong UI
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime maintenanceDate;
    private Long taskId;
}
