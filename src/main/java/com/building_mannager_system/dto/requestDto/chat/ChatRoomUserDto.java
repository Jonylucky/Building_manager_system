package com.building_mannager_system.dto.requestDto.chat;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatRoomUserDto {
    private Long id;             // ID của liên kết giữa người dùng và phòng chat
    private String username;     // Tên người dùng
    private String role;         // Vai trò: admin hoặc member
    private LocalDateTime joinedAt; // Thời điểm tham gia phòng
}
