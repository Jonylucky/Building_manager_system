package com.building_mannager_system.entity.property_manager;


import com.building_mannager_system.enums.ServiceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "riskAssessment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RiskAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer riskAssessmentID;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "maintenanceID", nullable = false)
    private MaintenanceHistory maintenanceHistory;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "contractorID", nullable = false)
    private Subcontractor contractor;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "deviceID", nullable = false)
    private Device device;

    private LocalDate assessmentDate; // Date of risk assessment

    @Column(nullable = false)
    private Integer riskProbability; // 1-10

    @Column(nullable = false)
    private Integer riskImpact; // 1-10

    @Column(nullable = false)
    private Integer riskDetection; // 1-10

    @Column(nullable = false)
    private Integer riskPriorityNumber; // Computed as Probability × Impact × Detection

    @Column(columnDefinition = "TEXT")
    private String mitigationAction; // Proposed mitigation action


}
