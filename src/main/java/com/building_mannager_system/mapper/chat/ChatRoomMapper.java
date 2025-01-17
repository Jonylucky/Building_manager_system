package com.building_mannager_system.mapper.chat;


import com.building_mannager_system.dto.requestDto.chat.ChatRoomDto;
import com.building_mannager_system.entity.chat.ChatRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatRoomMapper {

    ChatRoomMapper INSTANCE = Mappers.getMapper(ChatRoomMapper.class);

    // Chuyển từ Entity sang DTO
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "name",target = "username")
    ChatRoomDto toDto(ChatRoom chatRoom);

    // Chuyển từ DTO sang Entity
    @Mapping(source = "createdBy", target = "createdBy.id")

    ChatRoom toEntity(ChatRoomDto chatRoomDto);
    // Ánh xạ danh sách
    List<ChatRoomDto> toDtoList(List<ChatRoom> chatRooms);

    List<ChatRoom> toEntityList(List<ChatRoomDto> chatRoomDtos);


}
