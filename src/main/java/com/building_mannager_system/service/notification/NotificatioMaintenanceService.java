package com.building_mannager_system.service.notification;

import com.building_mannager_system.dto.requestDto.notificationDto.NotificationMaintenanceDto;
import com.building_mannager_system.dto.requestDto.verificationDto.RecipientDto;
import com.building_mannager_system.entity.notification.NotificationMaintenance;
import com.building_mannager_system.enums.StatusNotifi;
import com.building_mannager_system.mapper.notification.NotificationMaintenanceMapper;
import com.building_mannager_system.repository.notification.NotificationMAintenanceRepository;
import com.building_mannager_system.repository.notification.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationamintenaceService {

    @Autowired
    private NotificationMaintenanceMapper notificationMaintenanceMapper;
    @Autowired
    private NotificationMAintenanceRepository notificationRepository;
    @Autowired
    private RecipientService recipientService;
    // Tạo thông báo và chuyển đổi từ DTO sang Entity
    public NotificationMaintenanceDto createNotification(NotificationMaintenanceDto dto) {
        NotificationMaintenance entity = notificationMaintenanceMapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setStatus(StatusNotifi.PENDING);


        // Tạo danh sách người nhận (bao gồm kỹ thuật viên và quản lý kỹ thuật)
        List<RecipientDto> recipients = List.of(
                new RecipientDto(1, "TECHNICIAN", 101, "Technician A"),
                new RecipientDto(2, "TECHNICAL_MANAGER", 102, "Technical Manager")
        );

// Lặp qua danh sách và gọi phương thức createRecipient() cho mỗi recipient
        for (RecipientDto recipient : recipients) {
            recipientService.createRecipient(recipient);  // Gọi service để tạo recipient
        }
        NotificationMaintenance savedEntity = notificationRepository.save(entity);
        return notificationMaintenanceMapper.toDTO(savedEntity);
    }

    // Lấy tất cả thông báo và chuyển đổi từ Entity sang DTO
    public List<NotificationMaintenanceDto> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(notificationMaintenanceMapper::toDTO)
                .collect(Collectors.toList());
    }
}
