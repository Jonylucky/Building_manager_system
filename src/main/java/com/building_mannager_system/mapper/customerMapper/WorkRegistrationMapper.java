package com.building_mannager_system.mapper.customerMapper;

import com.building_mannager_system.dto.requestDto.work_registration.WorkRegistrationDto;
import com.building_mannager_system.entity.customer_service.work_registration.WorkRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WorkRegistrationMapper {
    WorkRegistrationMapper INSTANCE = Mappers.getMapper(WorkRegistrationMapper.class);

    // ✅ Chuyển từ Entity sang DTO
    @Mappings({
            @Mapping(source = "account.id", target = "accountId"),
            @Mapping(source = "account.username", target = "accountUsername"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "note", target = "note"),
            @Mapping(source = "drawingUrl", target = "constructionDrawingUrl")
    })
    WorkRegistrationDto toDto(WorkRegistration workRegistration);

    // ✅ Chuyển từ DTO sang Entity
    @Mappings({
            @Mapping(source = "accountId", target = "account.id"),
            @Mapping(target = "registrationID", ignore = true),  // Bỏ qua vì tự sinh
            @Mapping(source = "constructionDrawingUrl", target = "drawingUrl")
    })
    WorkRegistration toEntity(WorkRegistrationDto workRegistrationDto);
}
