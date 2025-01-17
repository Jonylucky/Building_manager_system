package com.building_mannager_system.service.chat;


import com.building_mannager_system.dto.requestDto.chat.ChatMessageDto;
import com.building_mannager_system.entity.chat.ChatMessage;
import com.building_mannager_system.mapper.chat.ChatMessageMapper;
import com.building_mannager_system.repository.chat.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatMessageService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatMessageMapper chatMessageMapper;

    public Page<ChatMessageDto> getMessagesByRoomIdWithPagination(Long roomId, int page, int size) {
        // ✅ Không cần trừ 1 ở đây nữa, truyền thẳng page vào Pageable
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("createdAt").descending());

        // ✅ Tìm các tin nhắn trong room với phân trang
        Page<ChatMessage> chatMessagesPage = chatMessageRepository.findByChatRoomId(roomId, pageable);

        // ✅ Chuyển đổi từ ChatMessage -> ChatMessageDto mà không cần đảo ngược
        List<ChatMessageDto> chatMessageDtoList = chatMessagesPage.stream()
                .map(chatMessageMapper::toDto)
                .collect(Collectors.toList());

        // ✅ Trả về Page<ChatMessageDto>
        return new PageImpl<>(chatMessageDtoList, pageable, chatMessagesPage.getTotalElements());
    }





    public ChatMessageDto sendMessage(ChatMessageDto chatMessageDto) {
        ChatMessage chatMessage = chatMessageMapper.toEntity(chatMessageDto);
        return chatMessageMapper.toDto(chatMessageRepository.save(chatMessage));
    }

    public void deleteMessage(Long id) {
        chatMessageRepository.deleteById(id);
    }
}
