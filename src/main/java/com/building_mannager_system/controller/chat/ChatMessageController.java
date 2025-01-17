package com.building_mannager_system.controller.chat;


import com.building_mannager_system.dto.requestDto.chat.ChatMessageDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.service.chat.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatmessages")
public class ChatMessageController {
    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Gửi tin nhắn qua RESTful API
    @PostMapping
    public ChatMessageDto sendMessage(@RequestBody ChatMessageDto chatMessageDto) {
        ChatMessageDto savedMessage = chatMessageService.sendMessage(chatMessageDto);
        messagingTemplate.convertAndSend("/topic/messages/" + chatMessageDto.getRoomId(), savedMessage);
        return savedMessage;
    }

    // Gửi tin nhắn qua WebSocket
    // ✅ Xử lý gửi tin nhắn từ WebSocket
    @MessageMapping("/sendMessage/{roomId}")
    public void sendMessageWebSocket(@DestinationVariable Long roomId, ChatMessageDto chatMessageDto) {
        chatMessageDto.setRoomId(roomId);
        ChatMessageDto savedMessage = chatMessageService.sendMessage(chatMessageDto);

        // ✅ Gửi tin nhắn đến tất cả người trong phòng
        messagingTemplate.convertAndSend("/topic/messages/" + roomId, savedMessage);
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<ApiResponce<Page<ChatMessageDto>>> getMessagesByRoomId(
            @PathVariable Long roomId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        // ✅ Gọi service để lấy dữ liệu phân trang
        Page<ChatMessageDto> chatMessages = chatMessageService.getMessagesByRoomIdWithPagination(roomId, page, size);

        System.out.println("page"+page);
        ApiResponce<Page<ChatMessageDto>> responce = new ApiResponce<>(200, chatMessages,"success");

        return ResponseEntity.ok(responce);
    }

    // Xóa tin nhắn theo ID
    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        chatMessageService.deleteMessage(id);
    }
}
