package com.building_mannager_system.mapper.customerMapper;

import com.building_mannager_system.dto.requestDto.customer.CustomerTypeDocumentDto;
import com.building_mannager_system.dto.requestDto.customer.CustomertypeDto;
import com.building_mannager_system.entity.customer_service.customer_manager.CustomertypeDocument;
import com.building_mannager_system.service.customer_service.CustomerTypeDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerTypeDocumentMapper {

    // Mapping từ Entity sang DTO
    @Mapping(source = "customerType.id", target = "customerTypeId")
    CustomerTypeDocumentDto toDto(CustomertypeDocument customertypeDocument);

    // Mapping từ DTO sang Entity
    @Mapping(source = "customerTypeId", target = "customerType.id")
    CustomertypeDocument toEntity(CustomerTypeDocumentDto customertypeDocumentDto);

    List<CustomerTypeDocumentDto> toDtoList(List<CustomertypeDocument> customertypeDocuments);

    List<CustomertypeDocument> toEntityList(List<CustomerTypeDocumentDto> customertypeDocumentDtos);
}


