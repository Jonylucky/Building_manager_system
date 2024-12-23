package com.building_mannager_system.dto.requestDto.propertyDto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ElectricityUsageDTO {
    private Integer id;
    private Integer meterId; // Thêm meterId vào DTO
    private BigDecimal startReading;
    private BigDecimal endReading;
    private BigDecimal usageAmount;
    private BigDecimal electricityRate;
    private BigDecimal electricityCost;
    private LocalDate readingDate;
    private String imageName;
    private String comments;
}
