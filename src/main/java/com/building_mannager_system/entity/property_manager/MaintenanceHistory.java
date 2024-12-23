package com.building_mannager_system.entity.property_manager;


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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "maintenance_id")
    private SystemMaintenanceService maintenanceService;

    private LocalDate performedDate;
    private String notes;
    private String technicianName;
    private String findings;
    private String resolution;
}
