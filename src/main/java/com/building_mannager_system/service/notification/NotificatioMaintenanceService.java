package com.building_mannager_system.service.notification;

import com.building_mannager_system.dto.requestDto.notificationDto.NotificationMaintenanceDto;
import com.building_mannager_system.dto.requestDto.verificationDto.RecipientDto;
import com.building_mannager_system.entity.notification.NotificationMaintenance;
import com.building_mannager_system.enums.StatusNotifi;
import com.building_mannager_system.mapper.notification.NotificationMaintenanceMapper;
import com.building_mannager_system.repository.notification.NotificationMAintenanceRepository;
import com.building_mannager_system.service.websocket.WebsocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificatioMaintenanceService {

    @Autowired
    private NotificationMaintenanceMapper notificationMaintenanceMapper;
    @Autowired
    private NotificationMAintenanceRepository notificationRepository;
    @Autowired
    private RecipientService recipientService;
    @Autowired
    private WebsocketService websocketService;
    // Tạo thông báo và chuyển đổi từ DTO sang Entity
    public NotificationMaintenanceDto createNotification(NotificationMaintenanceDto dto) {
        NotificationMaintenance entity = notificationMaintenanceMapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setMaintenanceDate(LocalDateTime.now());
        entity.setStatus(StatusNotifi.PENDING);


        NotificationMaintenance savedEntity = notificationRepository.save(entity);
        // Tạo danh sách người nhận (bao gồm kỹ thuật viên và quản lý kỹ thuật)

// Tạo danh sách người nhận (bao gồm kỹ thuật viên và quản lý kỹ thuật)
        List<RecipientDto> recipients = List.of(
                new RecipientDto(1,"TECHNICIAN", 101, "Technician A"),
                new RecipientDto(1, "TECHNICAL_MANAGER", 102, "Technical Manager")
        );

        // Lưu danh sách người nhận vào cơ sở dữ liệu
        for (RecipientDto recipient : recipients) {
            recipientService.createRecipient(recipient); // Gọi service để lưu recipient
        }

        // Gửi thông báo đến các loại người nhận mặc định
        List<String> types = List.of("TECHNICIAN", "TECHNICAL_MANAGER");
        websocketService.sendNotificationToRecipients(recipients, savedEntity, types);

        return notificationMaintenanceMapper.toDTO(savedEntity);
    }

    // Lấy tất cả thông báo và chuyển đổi từ Entity sang DTO
    public List<NotificationMaintenanceDto> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(notificationMaintenanceMapper::toDTO)
                .collect(Collectors.toList());
    }

  public  NotificationMaintenanceDto updateNotification(int id){

      // Tìm kiếm thông báo theo id
      NotificationMaintenance notificationMaintenance = notificationRepository.findById(id).orElse(null);

      // Kiểm tra nếu không tìm thấy thông báo, ném ngoại lệ
      if (notificationMaintenance == null) {
          throw new RuntimeException("Notification not found with id " + id);
      }

      // Cập nhật trạng thái của thông báo thành 'READ'
      notificationMaintenance.setStatus(StatusNotifi.READ);

      // Lưu thông báo đã cập nhật vào cơ sở dữ liệu
      NotificationMaintenance updatedNotification = notificationRepository.save(notificationMaintenance);

      // Chuyển đổi entity sang DTO và trả về
      return notificationMaintenanceMapper.toDTO(updatedNotification);
        
  }
}
