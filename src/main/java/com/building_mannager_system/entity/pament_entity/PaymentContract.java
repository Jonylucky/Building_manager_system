package com.building_mannager_system.entity.pament_entity;

import com.building_mannager_system.entity.customer_service.contact_manager.Contract;
import com.building_mannager_system.entity.customer_service.customer_manager.Customer;
import com.building_mannager_system.enums.PaymentStatus;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE)
@Table(name = "payment_contracts")
public class PaymentContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Tự động sinh giá trị cho PaymentId
    @Column(name = "PaymentId", nullable = false)
    private Integer paymentId;  // Mã định danh thanh toán

    @ManyToOne(fetch = FetchType.LAZY)  // Mối quan hệ Many-to-One với Contract
    @JoinColumn(name = "ContractId", referencedColumnName = "ContractId", nullable = false)
    private Contract contract;  // Liên kết với bảng Contract

    @ManyToOne(fetch = FetchType.LAZY)  // Mối quan hệ Many-to-One với Customer
    @JoinColumn(name = "CustomerId", referencedColumnName = "CustomerId", nullable = false)
    private Customer customer;  // Liên kết với bảng Customer

    @Column(name = "PaymentAmount", nullable = false)
    private BigDecimal paymentAmount;  // Số tiền thanh toán

    @Column(name = "PaymentDate", nullable = false)
    private LocalDate paymentDate;  // Ngày thanh toán

    @Enumerated(EnumType.STRING)
    @Column(name = "PaymentStatus", nullable = false)
    private PaymentStatus paymentStatus;  // Trạng thái thanh toán (Paid, Unpaid)
}