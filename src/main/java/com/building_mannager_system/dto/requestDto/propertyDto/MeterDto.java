package com.building_mannager_system.dto.requestDto.propertyDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MeterDto {
    private Integer id;
    private String serialNumber;
    private String type; // Loại đồng hồ (Một pha / Ba pha)
    private LocalDate installationDate; // Ngày lắp đặt
    private String location;
    private Integer officeId; // Id của văn phòng (nếu có)
}
