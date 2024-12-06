package com.building_mannager_system.dto.requestDto.propertyDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class HandoverStatusDto {
    private Integer id;
    private Integer officeId; // Map only the ID of the Office
    private LocalDate handoverDate;
    private String status;
    private String drawingFile;
    private String equipmentFile;
}
