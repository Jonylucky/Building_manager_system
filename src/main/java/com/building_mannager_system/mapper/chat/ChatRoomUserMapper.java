package com.building_mannager_system.mapper.chat;


import com.building_mannager_system.dto.requestDto.chat.ChatRoomUserDto;
import com.building_mannager_system.entity.chat.ChatRoomUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatRoomUserMapper {
    ChatRoomUserDto toDto(ChatRoomUser chatRoomUser);

    ChatRoomUser toEntity(ChatRoomUserDto chatRoomUserDto);

    List<ChatRoomUserDto> toDtoList(List<ChatRoomUser> chatRoomUsers);

    List<ChatRoomUser> toEntityList(List<ChatRoomUserDto> chatRoomUserDtos);
}
