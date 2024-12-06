package com.building_mannager_system.mapper.contractMapper;


import com.building_mannager_system.dto.requestDto.ContractDto.ContractDto;
import com.building_mannager_system.entity.customer_service.contact_manager.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);


    @Mapping(source = "officeID.id", target = "officeId")
    @Mapping(source = "customerID.id", target = "customerId")
    ContractDto toDto(Contract contract);

    @Mapping(source = "officeId", target = "officeID.id")
    @Mapping(source = "customerId", target = "customerID.id")
    Contract toEntity(ContractDto contractDto);




}
