package com.building_mannager_system.mapper.customerMapper;


import com.building_mannager_system.dto.requestDto.customer.CustomertypeDto;
import com.building_mannager_system.entity.customer_service.customer_manager.Customertype;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerTypesMapper {


    CustomertypeDto toDTO(Customertype customerType);

    Customertype toEntity(CustomertypeDto customerTypeDTO);

    List<CustomertypeDto> toDTOList(List<Customertype> customerTypes);
}
