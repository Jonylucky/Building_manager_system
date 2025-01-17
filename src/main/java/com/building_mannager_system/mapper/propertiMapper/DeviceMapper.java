package com.building_mannager_system.mapper.propertiMapper;

import com.building_mannager_system.dto.requestDto.propertyDto.DeviceDetailDto;
import com.building_mannager_system.dto.requestDto.propertyDto.DeviceDto;
import com.building_mannager_system.entity.property_manager.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    // Convert from Device Entity to DeviceDto
    @Mapping(source = "system.id", target = "systemId")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "deviceType.id", target = "deviceTypeId") // Corrected field name
    DeviceDto toDto(Device device);

    // Convert from DeviceDto to Device Entity
    @Mapping(source = "systemId", target = "system.id")
    @Mapping(source = "locationId", target = "location.id")
    @Mapping(source = "deviceTypeId", target = "deviceType.id") // Corrected field name
    Device toEntity(DeviceDto dto);

    // Convert a list of Device Entities to a list of DeviceDto
    List<DeviceDto> toDtoList(List<Device> devices);

    // Convert from Device Entity to DeviceDetailDto (including detailed attributes)

    @Mapping(source = "device.deviceId" ,target = "id")
    @Mapping(source = "system.systemName", target = "systemName") // Map system name if available
    @Mapping(source = "location.floor", target = "locationName")  // Map location floor if available
    @Mapping(source = "deviceType.typeName", target = "deviceTypeName")
    @Mapping(source = "maintenanceService.id",target = "maintenanceServiceId")
    @Mapping(source = "maintenanceService.subcontractor.id", target = "subcontractorId")
     @Mapping(source = "deviceName",target = "deviceName")
    @Mapping(source = "status",target = "status")
        @Mapping(source = "maintenanceHistories", target = "maintenanceHistories") // Map maintenance histories if needed
    @Mapping(source = "riskAssessments", target = "riskAssessments")           // Map risk assessments if needed
    DeviceDetailDto toDetailDto(Device device);
}

