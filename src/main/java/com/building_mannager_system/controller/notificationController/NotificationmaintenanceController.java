package com.building_mannager_system.controller.notificationController;

import com.building_mannager_system.dto.requestDto.notificationDto.NotificationMaintenanceDto;
import com.building_mannager_system.service.notification.NotificatioMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationmaintenanceController {
    @Autowired
    private NotificatioMaintenanceService notificatioMaintenanceService;
    // Endpoint để tạo thông báo bảo trì mới
    @PostMapping("/maintenance")
    @ResponseStatus(HttpStatus.CREATED)  // Trả về mã HTTP 201 khi tạo thành công
    public NotificationMaintenanceDto createNotification(@RequestBody NotificationMaintenanceDto dto) {
        return notificatioMaintenanceService.createNotification(dto);
    }

    // Endpoint để lấy tất cả các thông báo bảo trì
    @GetMapping
    public List<NotificationMaintenanceDto> getAllNotifications() {
        return notificatioMaintenanceService.getAllNotifications();
    }

    // Endpoint để lấy thông báo bảo trì theo ID
//    @GetMapping("/{id}")
//    public NotificationMaintenanceDto getNotificationById(@PathVariable Long id) {
//        return notificatioMaintenanceService.getNotificationById(id);
//    }

    // Endpoint để cập nhật thông báo bảo trì
//    @PutMapping("/{id}")
//    public NotificationMaintenanceDto updateNotification(
//            @PathVariable Long id,
//            @RequestBody NotificationMaintenanceDto dto) {
//        return notificatioMaintenanceService.updateNotification(id, dto);
//    }

    // Endpoint để xóa thông báo bảo trì
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)  // Trả về mã HTTP 204 khi xóa thành công
//    public void deleteNotification(@PathVariable Long id) {
//        notificatioMaintenanceService.deleteNotification(id);
//    }


}
