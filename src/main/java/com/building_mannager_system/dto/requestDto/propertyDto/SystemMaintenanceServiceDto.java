package com.building_mannager_system.dto.requestDto.propertyDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SystemMaintenanceServiceDto {
    private Long id;
    private String subcontractorName;
    private  Integer subcontractorId;
    private String systemType;
    private String maintenanceScope;
    private String frequency;
    private LocalDate nextScheduledDate;
    private String status;
}
