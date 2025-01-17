package com.building_mannager_system.dto.requestDto.chat;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatRoomDto {
    private Long id;
    private String username;
    private Long createdBy;
    private String status;
    private String message;
    private LocalDateTime createdAt;
}
