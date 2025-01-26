package com.building_mannager_system.service.notification;

import com.building_mannager_system.component.WebSocketEventListener;
import com.building_mannager_system.dto.requestDto.paymentDto.PaymentContractDto;
import com.building_mannager_system.dto.requestDto.verificationDto.ElectricityUsageVerificationDto;
import com.building_mannager_system.dto.requestDto.verificationDto.RecipientDto;
import com.building_mannager_system.entity.Account.Account;
import com.building_mannager_system.entity.notification.Notification;
import com.building_mannager_system.entity.notification.Recipient;
import com.building_mannager_system.entity.pament_entity.PaymentContract;
import com.building_mannager_system.enums.StatusNotifi;
import com.building_mannager_system.repository.chat.AccountRepository;
import com.building_mannager_system.untils.JsonUntils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
    @Autowired
    private AccountRepository accountRepository;

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
    public void sendElectricityUsageVerificationNotification(int customerId, ElectricityUsageVerificationDto verificationElectricityUsageDto) {
        try {
            // Tìm tài khoản dựa trên customerId
            Optional<Account> optionalAccount = accountRepository.findByCustomer_Id(customerId);

            if (optionalAccount.isEmpty()) {
                return;  // Thoát phương thức nếu không tìm thấy tài khoản
            }

            Account account = optionalAccount.get();

            // Kiểm tra trạng thái người dùng (online hay offline)
            boolean isOnline = Boolean.TRUE.equals(account.getIsOnline()); // Kiểm tra nếu người dùng online

            if (isOnline) {
                // Nếu online, gửi thông báo ngay lập tức qua WebSocket
                messagingTemplate.convertAndSend("/topic/electricityUsageVerification/" + account.getId(), verificationElectricityUsageDto);
                System.out.println("Electricity usage verification notification sent successfully to online user!");
            } else {
                // Người dùng offline, lưu thông báo vào hệ thống

                // Chuyển đổi thông tin thông báo thành JSON
                String message = JsonUntils.toJson(verificationElectricityUsageDto);

                // Tạo đối tượng Recipient
                RecipientDto recipientDto = new RecipientDto();
                recipientDto.setType("CustomeId");
                recipientDto.setName("Electricity usage verification");
                recipientDto.setReferenceId(customerId);  // Reference ID có thể là contactId hoặc ID của đối tượng liên quan

                // Tạo bản ghi recipient
                Recipient recipient = recipientService.createRecipient(recipientDto);

                // Tạo thông báo
                Notification notification = new Notification();
                notification.setRecipient(recipient);
                notification.setMessage(message);
                notification.setStatus(StatusNotifi.PENDING);  // Đánh dấu trạng thái là PENDING
                notification.setCreatedAt(LocalDateTime.now());

                // Lưu thông báo vào cơ sở dữ liệu
                notificationService.createNotification(notification);
                System.out.println("Electricity usage verification notification saved for offline user!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error sending electricity usage verification notification to customer: " + e.getMessage());
        }
    }




}
