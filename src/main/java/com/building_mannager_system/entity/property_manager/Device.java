package com.building_mannager_system.entity.property_manager;


import com.building_mannager_system.entity.customer_service.officeSpaceAllcation.Location;
import com.building_mannager_system.enums.DeviceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;

    @ManyToOne
    @JoinColumn(name = "system_id")
    private Systems system;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    private String deviceName;
    private LocalDate installationDate;
    private Integer lifespan;

    @Enumerated(EnumType.STRING)
    private DeviceStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "device_type_id", nullable = false)
    private DeviceType deviveType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_service_id")
    private SystemMaintenanceService maintenanceService;

}
