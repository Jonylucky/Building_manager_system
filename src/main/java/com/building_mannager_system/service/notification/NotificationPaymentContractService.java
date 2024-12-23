package com.building_mannager_system.service.notification;

import com.building_mannager_system.component.WebSocketEventListener;
import com.building_mannager_system.dto.requestDto.paymentDto.PaymentContractDto;
import com.building_mannager_system.dto.requestDto.verificationDto.ElectricityUsageVerificationDto;
import com.building_mannager_system.dto.requestDto.verificationDto.RecipientDto;
import com.building_mannager_system.entity.notification.Notification;
import com.building_mannager_system.entity.notification.Recipient;
import com.building_mannager_system.entity.pament_entity.PaymentContract;
import com.building_mannager_system.enums.StatusNotifi;
import com.building_mannager_system.untils.JsonUntils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationPaymentContractService {

    @Autowired
    private  SimpMessagingTemplate messagingTemplate;

    @Autowired
    private WebSocketEventListener webSocketEventListener;
    @Autowired
    private  NotificationService notificationService;
    @Autowired
    private  RecipientService recipientService;

    // Gửi thông báo thanh toán đến khách hàng
    public void sendPaymentRequestNotificationToCustomer(PaymentContractDto paymentContractDto, int customerId) {
        System.out.println("Sending notification to customer: " + customerId);
        try {
            System.out.println("Sending notification to customer: " + customerId);
            messagingTemplate.convertAndSend(

                    "/topic/notifications/"+customerId,
                    paymentContractDto
            );
            System.out.println("Notification sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error sending notification to customer: " + e.getMessage());
        }

    }

    // Gửi thông báo thanh toán thành công đến khách hàng
    public void sendPaymentConfirmationToCustomer(PaymentContract paymentContract, int customerId) {
        messagingTemplate.convertAndSend("/user/" + customerId + "/queue/paymentNotifications", paymentContract);
    }
    // Gửi thông báo xác nhận sử dụng điện đến khách hàng
    public void sendElectricityUsageVerificationNotification(int contactId, ElectricityUsageVerificationDto verificationElectricityUsageDto) {
        try {
            System.out.println("Sending electricity usage verification notification to contactID: " + contactId);

            // Kiểm tra trạng thái người dùng (online hay offline)
            boolean isOnline = webSocketEventListener.isUserOnline(String.valueOf(contactId)); // Kiểm tra nếu người dùng online

            if (isOnline) {
                // Nếu online, gửi thông báo ngay lập tức
                messagingTemplate.convertAndSend("/topic/electricityUsageVerification/" + contactId, verificationElectricityUsageDto);
                System.out.println("Electricity usage verification notification sent successfully to online user!");
            } else {
                // Tạo đối tượng Recipient
                String mesage = JsonUntils.toJson(verificationElectricityUsageDto);
                RecipientDto recipientDto = new RecipientDto();

                recipientDto.setType("Contact");
                recipientDto.setName("Electricity usage verification");
                recipientDto.setReferenceId(contactId); // Reference ID có thể là contactId hoặc ID của đối tượng liên quan


                 Recipient recipient = recipientService.createRecipient(recipientDto);

                Notification notification = new Notification();
                notification.setRecipient(recipient);
                notification.setMessage(mesage);

                notification.setStatus(StatusNotifi.PENDING); // Đánh dấu là PENDING
                notification.setCreatedAt(LocalDateTime.now());

           notificationService.createNotification(notification); // Lưu thông báo vào cơ sở dữ liệu
                System.out.println("Electricity usage verification notification saved for offline user!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error sending electricity usage verification notification to customer: " + e.getMessage());
        }
    }



}
