package com.building_mannager_system.dto.requestDto.paymentDto;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentContractDto {
    private Integer paymentId;  // Mã thanh toán
    private Integer contractId;  // ID hợp đồng
    private Integer customerId;  // ID khách hàng
    private BigDecimal paymentAmount;  // Số tiền thanh toán
    private LocalDate paymentDate;  // Ngày thanh toán
    private String paymentStatus;  // Trạng thái thanh toán (Paid, Unpaid)
}
