package com.building_mannager_system.mapper.customerMapper;


import com.building_mannager_system.dto.requestDto.work_registration.RepairRequestDto;
import com.building_mannager_system.entity.customer_service.work_registration.RepairRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RepairRequestMapper {
    RepairRequestMapper INSTANCE = Mappers.getMapper(RepairRequestMapper.class);

    // ✅ Chuyển từ Entity sang DTO
    @Mappings({
            @Mapping(source = "account.id", target = "accountId"),
            @Mapping(source = "account.username", target = "accountUsername")
    })
    RepairRequestDto toDto(RepairRequest repairRequest);

    // ✅ Chuyển từ DTO sang Entity
    @Mappings({
            @Mapping(source = "accountId", target = "account.id"),
            @Mapping(target = "requestID", ignore = true)  // Bỏ qua trường tự sinh
    })
    RepairRequest toEntity(RepairRequestDto repairRequestDto);
}
