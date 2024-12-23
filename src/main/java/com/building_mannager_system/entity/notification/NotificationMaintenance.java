package com.building_mannager_system.entity.notification;


import com.building_mannager_system.entity.property_manager.MaintenanceTask;
import com.building_mannager_system.enums.StatusNotifi;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification_maintenance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "recipient", nullable = false)
    private String recipient;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusNotifi status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "maintenance_date", nullable = false)
    private LocalDateTime maintenanceDate;
    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id", nullable = false)
    private MaintenanceTask maintenanceTask; // Liên kết với công việc bảo trì cụ thể
}
