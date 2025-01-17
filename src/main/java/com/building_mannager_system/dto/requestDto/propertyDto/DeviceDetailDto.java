package com.building_mannager_system.dto.requestDto.propertyDto;


import com.building_mannager_system.entity.customer_service.officeSpaceAllcation.Location;
import com.building_mannager_system.entity.property_manager.Device;
import com.building_mannager_system.entity.property_manager.DeviceType;
import com.building_mannager_system.entity.property_manager.Systems;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class DeviceDetailDto {
   private  Long id;
    private Long subcontractorId;
    private String systemName;
    private String deviceName;
    private Long maintenanceServiceId;
    private String status;
    private Integer lifespan;
    private String locationName;
    private String deviceTypeName;
    private List<MaintenanceHistoryDto> maintenanceHistories;
    private List<RiskAssessmentDto> riskAssessments;
    // Other relevant fields
}

