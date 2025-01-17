package com.building_mannager_system.entity.property_manager;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "maintenance_history")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id") // Ensures the column name matches the database
    private Long id;

    @ManyToOne
    @JoinColumn(name = "system_maintenance_service_id")
    @JsonIgnore
    private SystemMaintenanceService maintenanceService;
    // Mối quan hệ với Device
    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    @JsonIgnore
    private Device device;
    private LocalDate performedDate;
    private String notes;
    private String technicianName;
    private String findings;
    private String resolution;
}
