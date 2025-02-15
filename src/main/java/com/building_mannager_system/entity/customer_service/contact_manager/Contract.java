package com.building_mannager_system.entity.customer_service.contact_manager;

import com.building_mannager_system.entity.customer_service.customer_manager.Customer;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE)
@Table(name = "contract")
public class Contract {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContractId", nullable = false)
    private Integer id;

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "EndDate")
    private LocalDate endDate;

    @Column(name = "TotalAmount", precision = 15, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "FileName")
    private String fileName;
    @Lob
    @Column(name = "LeaseStatus")
    private String leaseStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OfficeID")
    private Office officeID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerID")
    private Customer customerID;

}