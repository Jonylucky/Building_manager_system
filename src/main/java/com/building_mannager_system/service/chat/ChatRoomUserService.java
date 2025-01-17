package com.building_mannager_system.service.chat;


import com.building_mannager_system.dto.requestDto.chat.ChatRoomUserDto;
import com.building_mannager_system.entity.Account.Account;
import com.building_mannager_system.entity.chat.ChatRoomUser;
import com.building_mannager_system.mapper.chat.ChatRoomUserMapper;
import com.building_mannager_system.repository.chat.ChatRoomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatRoomUserService {
    @Autowired
    private ChatRoomUserRepository chatRoomUserRepository;

    @Autowired
    private ChatRoomUserMapper chatRoomUserMapper;

    public List<ChatRoomUserDto> getUsersByChatRoomId(Long roomId) {
        return chatRoomUserRepository.findAllByChatRoomId(roomId)
                .stream()
                .map(chatRoomUserMapper::toDto)
                .collect(Collectors.toList());
    }

    public ChatRoomUserDto addUserToChatRoom(ChatRoomUserDto chatRoomUserDto) {
        ChatRoomUser chatRoomUser = chatRoomUserMapper.toEntity(chatRoomUserDto);
        return chatRoomUserMapper.toDto(chatRoomUserRepository.save(chatRoomUser));
    }
    public List<Account> getAccountsByRoomId(Long roomId) {
        // Lấy danh sách ChatRoomUser từ roomId
        List<ChatRoomUser> chatRoomUsers = chatRoomUserRepository.findByChatRoomId(roomId);

        // Trích xuất danh sách Account từ ChatRoomUser
        return chatRoomUsers.stream()
                .map(ChatRoomUser::getUser)
                .collect(Collectors.toList());
    }
    public void removeUserFromChatRoom(Long id) {
        chatRoomUserRepository.deleteById(id);
    }
}
