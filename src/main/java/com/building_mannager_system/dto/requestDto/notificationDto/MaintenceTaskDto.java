package com.building_mannager_system.dto.requestDto.notificationDto;

import com.building_mannager_system.enums.MaintenanceType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MaintenceTaskDto {
    private Long id;
    private String taskName;
    private String taskDescription;
    private MaintenanceType maintenanceType; // Loại bảo trì (Định kỳ / Sự cố đột xuất)
    private String assignedTo; // Người thực hiện công việc
    private String assignedToPhone; // Số điện thoại của người thực hiện công việc
    private Integer expectedDuration; // Thời gian dự kiến hoàn thành công việc (phút)
    private List<Long> notificationIds; // Danh sách ID thông báo bảo trì
}
