package com.building_mannager_system.dto.requestDto.oficeSapceAllcationDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {

    private int locationID;
    private String locationName;
    private String buildingSection;
    private String description;
}
