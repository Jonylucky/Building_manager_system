package com.building_mannager_system.entity.property_manager;

import com.building_mannager_system.enums.MaintenanceFrequency;
import com.building_mannager_system.enums.MaintenanceStatus;
import com.building_mannager_system.enums.ServiceType;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "system_maintenance_services")
public class SystemMaintenanceService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subcontractor_id")
    private Subcontractor subcontractor;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    private String maintenanceScope;

    @Enumerated(EnumType.STRING)
    private MaintenanceFrequency frequency;

    private LocalDate nextScheduledDate;

    @Enumerated(EnumType.STRING)
    private MaintenanceStatus status;
}
