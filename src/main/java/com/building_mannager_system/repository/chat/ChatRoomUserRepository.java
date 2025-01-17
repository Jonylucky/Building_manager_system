package com.building_mannager_system.repository.chat;

import com.building_mannager_system.entity.Account.Account;
import com.building_mannager_system.entity.chat.ChatRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Long> {
    // Lấy danh sách người dùng trong một phòng chat
    List<ChatRoomUser> findAllByChatRoomId(Long roomId);

    // Kiểm tra xem một người dùng đã tham gia phòng chưa
    boolean existsByChatRoomIdAndUserId(Long roomId, Long userId);
   // Kiểm tra sự tồn tại dựa trên username của người dùng trong Account
    boolean existsByUser_Username(String username);

    // Kiểm tra sự tồn tại dựa trên email của Account
    boolean existsByUser_Email(String email);
    List<ChatRoomUser> findByChatRoomId(Long roomId);
}
