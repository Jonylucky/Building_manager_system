package com.building_mannager_system.service.websocket;

import com.building_mannager_system.dto.requestDto.verificationDto.RecipientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebsocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    // Phương thức gửi thông báo qua WebSocket với lọc theo nhiều type

    public void sendNotificationToRecipients(List<RecipientDto> recipients, Object message, List<String> types) {
        // Lọc recipient theo các type được truyền vào
        List<RecipientDto> filteredRecipients = recipients.stream()
                .filter(r -> types.contains(r.getType())) // Kiểm tra xem type của recipient có nằm trong danh sách types không
                .toList();

        // Lặp qua các recipient đã lọc và gửi thông báo
        for (RecipientDto recipient : filteredRecipients) {
            System.out.println(recipient.getType());
            // Tạo địa chỉ gửi tin nhắn (ví dụ: "/topic/recipient/{id}")
            String destination = "/topic/maintenance/" + recipient.getReferenceId();

            // Gửi tin nhắn tới destination
            //converAndSendToUSer();
            messagingTemplate.convertAndSend(destination, message);
            System.out.println("Notification sent to recipient ID: " + recipient.getReferenceId());
        }
    }

}
