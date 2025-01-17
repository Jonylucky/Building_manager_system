package com.building_mannager_system.dto.requestDto.chat;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageDto {
    private Long id;            // ID duy nhất của tin nhắn
    private Long roomId;        // ID của phòng chat
    private Long senderId;
    private String status;
    private String imageUrl;// ID của người gửi tin nhắn
    private String message;     // Nội dung tin nhắn
    private LocalDateTime createdAt;  // Thời gian gửi tin nhắn

}
