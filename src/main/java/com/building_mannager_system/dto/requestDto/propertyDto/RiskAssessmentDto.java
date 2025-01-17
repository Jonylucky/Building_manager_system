package com.building_mannager_system.dto.requestDto.propertyDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RiskAssessmentDto {
    private Integer riskAssessmentID;
    private Integer maintenanceID; // Foreign Key (MaintenanceHistory)
    private Integer contractorID; // Foreign Key (Subcontractor)
    private Integer deviceID; // Foreign Key (Device)
    private LocalDate assessmentDate; // Date of assessment
    private Integer riskProbability; // 1-10
    private Integer riskImpact; // 1-10
    private Integer riskDetection; // 1-10
    private Integer riskPriorityNumber; // Computed field
    private String mitigationAction; // Proposed action

}
