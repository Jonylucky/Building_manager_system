package com.building_mannager_system.dto.requestDto.propertyDto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MaintenanceHistoryDto {
    private Long id;
    private Long maintenanceId;
    private Long deviceId;
    private LocalDate performedDate;
    private String notes;
    private String technicianName;
    private String findings;
    private String resolution;
}
