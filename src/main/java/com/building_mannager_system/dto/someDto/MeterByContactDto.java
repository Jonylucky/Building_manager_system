package com.building_mannager_system.dto.someDto;

import com.building_mannager_system.dto.requestDto.customer.ContactDto;
import com.building_mannager_system.dto.requestDto.propertyDto.MeterDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeterByContactDto {
    private MeterDto meter;
    private ContactDto contact;
}
