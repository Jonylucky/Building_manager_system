package com.building_mannager_system.dto.requestDto.systemDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SystemDto {
    private Long systemId;
    private String systemName;
    private String description;
    private int maintenanceCycle;
    private List<SubcontractorDto> subcontractors;
}
