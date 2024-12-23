package com.building_mannager_system.controller.paymentController;


import com.building_mannager_system.dto.requestDto.paymentDto.PaymentContractDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.entity.pament_entity.PaymentContract;
import com.building_mannager_system.service.notification.NotificationPaymentContractService;
import com.building_mannager_system.service.payment.PaymentContractService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/payment")
public class PaymentContractController {
    @Autowired
    private PaymentContractService paymentContractService;
    @Autowired
    private NotificationPaymentContractService notificationPaymentContractService;


    @GetMapping("/{id}")
    public ApiResponce<PaymentContractDto> getPaymentByCustomerId(@PathVariable int id) {
        // Call the service layer to retrieve the data
        PaymentContractDto paymentContract = paymentContractService.getpaymentContractByCustomerId(id);

        // Return the data wrapped in an ApiResponse object
        return new ApiResponce<>(200,paymentContract,"Payment contracts retrieved successfully");
    }

    @GetMapping("/unpaid/{id}")
    public ApiResponce<PaymentContractDto> getUnpaidPaymentContractByCustomerId (@PathVariable int id) {
        // Call the service layer to retrieve the data
        PaymentContractDto paymentContract = paymentContractService.getPaymentContractByCustomerIdAndUnpaidStatus(id);

        // Return the data wrapped in an ApiResponse object
        return new ApiResponce<>(200,paymentContract,"Payment contracts retrieved successfully");
    }
    // Kế toán gửi thông báo thanh toán cho khách hàng
    @PostMapping("/sendPaymentRequest/{id}")
    public ResponseEntity<String> sendPaymentRequest(@PathVariable int id) {
        try {
            PaymentContractDto paymentContractDto = paymentContractService.getpaymentContractByCustomerId(id);


            notificationPaymentContractService.sendPaymentRequestNotificationToCustomer(paymentContractDto, paymentContractDto.getCustomerId());

            return ResponseEntity.ok("Payment request sent to customer.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending payment request.");
        }
    }




    @PostMapping("/create_payment")
    public ResponseEntity<ApiResponce<PaymentContractDto>> createPayment(@RequestBody PaymentContractDto paymentContractDto) {
        try {
            // Call the service to create the payment contract
            PaymentContractDto createdPaymentContract = paymentContractService.createPaymentContract(paymentContractDto);
            System.out.println(paymentContractDto.getContractId());
            // Return the response wrapped in ApiResponse
            ApiResponce<PaymentContractDto> response = new ApiResponce<>();
            response.setCode(200);  // Success code
            response.setStatus("Success");
            response.setData(createdPaymentContract);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Handle exceptions and return error response
            ApiResponce<PaymentContractDto> errorResponse = new ApiResponce<>();
            errorResponse.setCode(400);  // Bad Request
            errorResponse.setStatus("Error");
            errorResponse.setData(null);

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    // Nhận thông báo từ khách hàng (ví dụ: thanh toán đã hoàn tất)
    @MessageMapping("/paymentResponse")  // Lắng nghe các yêu cầu từ khách hàng tại "/app/paymentResponse"
    @SendTo("/topic/paymentConfirmation")  // Gửi phản hồi lại cho tất cả các client
    public String receivePaymentResponse(PaymentContractDto paymentResponse) {
        // Xử lý thông báo thanh toán từ khách hàng
        System.out.println("Received payment response: " + paymentResponse.getPaymentStatus());

        // Gửi thông báo thanh toán đã hoàn tất
        return "Payment confirmed: " + paymentResponse.getPaymentStatus();
    }

}
