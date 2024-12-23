package com.building_mannager_system.dto.requestDto.propertyDto;

import com.building_mannager_system.enums.DeviceStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DeviceDto {
    private Long deviceId;
    private Long systemId;
    private Long locationId;
    private long deviceTypeId;

    private String deviceName;
    private LocalDate installationDate;
    private Integer lifespan;
    private DeviceStatus status;

}
