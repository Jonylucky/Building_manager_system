package com.building_mannager_system.mapper.propertiMapper;

import com.building_mannager_system.dto.requestDto.propertyDto.MaintenanceHistoryDto;
import com.building_mannager_system.entity.property_manager.Device;
import com.building_mannager_system.entity.property_manager.MaintenanceHistory;
import com.building_mannager_system.entity.property_manager.SystemMaintenanceService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MaintenanceHistoryMapper {

    @Mapping(source = "maintenanceService.id", target = "maintenanceId")
    @Mapping(source = "device.deviceId", target = "deviceId")
    MaintenanceHistoryDto toDto(MaintenanceHistory history);

    @Mapping(source = "maintenanceId", target = "maintenanceService.id")
    @Mapping(source = "deviceId", target = "device.deviceId")
    MaintenanceHistory toEntity(MaintenanceHistoryDto dto);


    List<MaintenanceHistoryDto> toDtoList(List<MaintenanceHistory> histories);
}
