package com.building_mannager_system.dto.requestDto.systemDto;


import com.building_mannager_system.enums.ServiceType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class SubcontractorDto {
    private Integer id;
    private String name;
    private String phone;

    private ServiceType serviceType;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private BigDecimal rating;
    private Long systemId; // Reference to the System entity
}
