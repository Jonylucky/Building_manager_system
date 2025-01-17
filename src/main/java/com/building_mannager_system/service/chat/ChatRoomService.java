package com.building_mannager_system.service.chat;


import com.building_mannager_system.dto.requestDto.chat.ChatRoomDto;
import com.building_mannager_system.entity.Account.Account;
import com.building_mannager_system.entity.chat.ChatRoom;
import com.building_mannager_system.entity.chat.ChatRoomUser;
import com.building_mannager_system.mapper.chat.ChatRoomMapper;
import com.building_mannager_system.repository.chat.AccountRepository;
import com.building_mannager_system.repository.chat.ChatRoomRepository;
import com.building_mannager_system.repository.chat.ChatRoomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatRoomUserRepository chatRoomUserRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ChatRoomMapper chatRoomMapper;

    public ChatRoomDto createPrivateChatRoom(Long account1Id, Long account2Id) {
        Optional<Account> account1 = accountRepository.findById(account1Id);
        Optional<Account> account2 = accountRepository.findById(account2Id);

        if (account1.isEmpty() || account2.isEmpty()) {
            throw new RuntimeException("Tài khoản không tồn tại");
        }

        // ✅ Kiểm tra xem phòng chat đã tồn tại giữa hai tài khoản chưa
        List<Long> userIds = Arrays.asList(account1Id, account2Id);
        List<ChatRoom> existingRooms = chatRoomRepository.findByIsPrivateTrueAndUsers_User_IdIn(userIds);

        if (!existingRooms.isEmpty()) {
            return chatRoomMapper.toDto(existingRooms.get(0)); // ✅ Trả về phòng chat nếu đã tồn tại
        }

        // ✅ Nếu chưa tồn tại, tạo phòng chat mới
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(account1.get().getUsername());
        chatRoom.setIsPrivate(true);
        chatRoom.setCreatedBy(account1.get());
        chatRoom.setCreatedAt(LocalDateTime.now());
        chatRoom.setUpdatedAt(LocalDateTime.now());

        ChatRoom savedRoom = chatRoomRepository.save(chatRoom);

        // ✅ Thêm người dùng vào phòng
        ChatRoomUser user1 = new ChatRoomUser(1l,savedRoom,account1.get(), ChatRoomUser.Role.ADMIN,LocalDateTime.now());
        ChatRoomUser user2 = new ChatRoomUser(2L,savedRoom, account2.get(), ChatRoomUser.Role.MEMBER,LocalDateTime.now());
        chatRoomUserRepository.saveAll(Arrays.asList(user1, user2));

        return chatRoomMapper.toDto(savedRoom);
    }


    /**
     * ✅ Tìm tất cả phòng chat của một người dùng
     */
    public List<ChatRoomDto> getAllRoomsByUserId(Long userId) {
        List<ChatRoom> rooms = chatRoomRepository.findByUsers_User_Id(userId);
        return rooms.stream().map(chatRoomMapper::toDto).collect(Collectors.toList());
    }
    public ChatRoomDto createGroupChatRoom(ChatRoomDto chatRoomDto) {
        ChatRoom chatRoom = chatRoomMapper.toEntity(chatRoomDto);
        chatRoom.setIsPrivate(false);
        chatRoom = chatRoomRepository.save(chatRoom);
        return chatRoomMapper.toDto(chatRoom);
    }


    public List<ChatRoomDto> getAllChatRooms() {
        return chatRoomMapper.toDtoList(chatRoomRepository.findAll());
    }

    public void deleteChatRoom(Long id) {
        chatRoomRepository.deleteById(id);
    }
}
