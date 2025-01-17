package com.building_mannager_system.controller.chat;

import com.building_mannager_system.dto.requestDto.chat.ChatRoomUserDto;
import com.building_mannager_system.service.chat.ChatRoomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatroomusers")
public class ChatRoomUserController {
    @Autowired
    private ChatRoomUserService chatRoomUserService;

    @GetMapping("/room/{roomId}")
    public List<ChatRoomUserDto> getUsersByChatRoom(@PathVariable Long roomId) {
        return chatRoomUserService.getUsersByChatRoomId(roomId);
    }

    @PostMapping
    public ChatRoomUserDto addUserToChatRoom(@RequestBody ChatRoomUserDto chatRoomUserDto) {
        return chatRoomUserService.addUserToChatRoom(chatRoomUserDto);
    }

    @DeleteMapping("/{id}")
    public void removeUserFromChatRoom(@PathVariable Long id) {
        chatRoomUserService.removeUserFromChatRoom(id);
    }
}
