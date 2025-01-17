package com.building_mannager_system.mapper.chat;


import com.building_mannager_system.dto.requestDto.chat.ChatMessageDto;
import com.building_mannager_system.entity.chat.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {


    @Mapping(source = "chatRoom.id",target = "roomId")
    @Mapping(source = "user.id",target= "senderId")
    @Mapping(source = "imageUrl",target = "imageUrl")
    @Mapping(source = "status",target="status")
    ChatMessageDto toDto(ChatMessage chatMessage);


    @Mapping(source = "roomId",target = "chatRoom.id")
    @Mapping(source = "senderId",target= "user.id")
    @Mapping(source = "imageUrl",target = "imageUrl")
    @Mapping(source = "status",target="status")
    ChatMessage toEntity(ChatMessageDto chatMessageDto);

    List<ChatMessageDto> toDtoList(List<ChatMessage> chatMessages);

    List<ChatMessage> toEntityList(List<ChatMessageDto> chatMessageDtos);


}
