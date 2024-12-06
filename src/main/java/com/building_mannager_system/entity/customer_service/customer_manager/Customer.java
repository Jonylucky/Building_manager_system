package com.building_mannager_system.entity.customer_service.customer_manager;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE)
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerId", nullable = false)
    private Integer id;

    @Column(name = "CompanyName", nullable = false)
    private String companyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerTypeId")
    private Customertype customerType;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone", length = 20)
    private String phone;

    @Column(name = "Address")
    private String address;

    @Lob
    @Column(name = "Status")
    private String status;

    @Column(name = "DirectorName")
    private String directorName;

    @Column(name = "Birthday")
    private LocalDate birthday;

}
