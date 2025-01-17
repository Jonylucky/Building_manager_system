package com.building_mannager_system.controller.chat;


import com.building_mannager_system.dto.requestDto.chat.ChatRoomDto;
import com.building_mannager_system.service.chat.ChatRoomService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    // Tạo phòng chat riêng giữa hai tài khoản
    @PostMapping("/private")
    public ChatRoomDto createPrivateChatRoom(@RequestParam Long account1Id, @RequestParam Long account2Id) {
        return chatRoomService.createPrivateChatRoom(account1Id, account2Id);
    }

    // Tạo phòng chat nhóm
    @PostMapping("/group")
    public ChatRoomDto createGroupChatRoom(@RequestBody ChatRoomDto chatRoomDto) {
        return chatRoomService.createGroupChatRoom(chatRoomDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ChatRoomDto>> getChatRoomByAccountId(@PathVariable("id") Long id) {
        List<ChatRoomDto> chatRooms = chatRoomService.getAllRoomsByUserId(id);

        if (chatRooms.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList());
        }
        return ResponseEntity.ok(chatRooms);
    }

    // Lấy tất cả phòng chat
    @GetMapping
    public List<ChatRoomDto> getAllChatRooms() {
        return chatRoomService.getAllChatRooms();
    }

    // Xóa phòng chat theo ID
    @DeleteMapping("/{id}")
    public void deleteChatRoom(@PathVariable Long id) {
        chatRoomService.deleteChatRoom(id);
    }
}
