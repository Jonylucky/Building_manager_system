package com.building_mannager_system.repository.chat;

import com.building_mannager_system.entity.chat.ChatMessage;
import com.building_mannager_system.entity.chat.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface ChatMessageRepository  extends JpaRepository<ChatMessage, Long> {

    Page<ChatMessage> findByChatRoomId(
            Long roomId,
            Pageable pageable);
    List<ChatRoom> findDistinctByUserId(Long userId);










}
