package com.building_mannager_system.service.notification;

import com.building_mannager_system.dto.requestDto.paymentDto.PaymentContractDto;
import com.building_mannager_system.entity.pament_entity.PaymentContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationPaymentContractService {

    @Autowired
    private  SimpMessagingTemplate messagingTemplate;
    // Gửi thông báo thanh toán đến khách hàng
    public void sendPaymentRequestNotificationToCustomer(PaymentContractDto paymentContractDto, int customerId) {
        System.out.println("Sending notification to customer: " + customerId);
        try {
            System.out.println("Sending notification to customer: " + customerId);
            messagingTemplate.convertAndSend(

                    "/topic/paymentNotifications",
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

}
