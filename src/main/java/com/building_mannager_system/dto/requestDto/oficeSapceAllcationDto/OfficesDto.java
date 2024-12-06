package com.building_mannager_system.dto.requestDto.oficeSapceAllcationDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfficesDto {
    private Integer id;
    private int locationId;  // assuming Location has a `locationId` field
    private BigDecimal area;
    private BigDecimal rentPrice;
    private BigDecimal serviceFee;
    private String status;
    private String drawingFile;

}
