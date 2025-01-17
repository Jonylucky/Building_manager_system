package com.building_mannager_system.entity.chat;


import com.building_mannager_system.entity.Account.Account;
import com.building_mannager_system.enums.MessageStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false) // Liên kết đến ChatRoom
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Liên kết đến Account
    private Account user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "status", nullable = false)

    @Enumerated(EnumType.STRING) // Lưu trạng thái dưới dạng chuỗi
    private MessageStatus status = MessageStatus.SENT; // Mặc định là Sent

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
