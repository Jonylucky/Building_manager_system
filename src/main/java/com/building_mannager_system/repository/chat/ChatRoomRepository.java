package com.building_mannager_system.repository.chat;

import com.building_mannager_system.entity.Account.Account;
import com.building_mannager_system.entity.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
        List<ChatRoom> findByIsPrivateTrueAndUsers_User_IdIn(List<Long> userIds);

        /**
         * ✅ Tìm tất cả các phòng chat mà một người dùng đang tham gia
         */
        List<ChatRoom> findByUsers_User_Id(Long userId);
}
