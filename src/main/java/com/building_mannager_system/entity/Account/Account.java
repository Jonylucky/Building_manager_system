package com.building_mannager_system.entity.Account;


import com.building_mannager_system.entity.customer_service.customer_manager.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String username;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "FK_account_customer"))
    private Customer customer;


    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 500)
    private String avatar;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "is_online")
    private Boolean isOnline = false;  // Mặc định là offline khi tài khoản được tạo
    @Column(name = "is_online_websocket")
    private Boolean isOnlineWebsocket = false;  // Mặc định là offline khi tài khoản được tạo
}
