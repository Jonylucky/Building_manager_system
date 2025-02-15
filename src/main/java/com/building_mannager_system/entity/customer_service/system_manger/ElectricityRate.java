package com.building_mannager_system.entity.customer_service.system_manger;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "electricity_rate")
public class ElectricityRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // ID tự động tăng

    @Column(name = "tier_name", nullable = false)
    private String tierName;  // Tên bậc giá

    @Column(name = "min_usage", nullable = false)
    private Integer minUsage;  // Mức tiêu thụ tối thiểu (kWh)

    @Column(name = "max_usage")
    private Integer maxUsage;  // Mức tiêu thụ tối đa (kWh)

    @Column(name = "rate", nullable = false)
    private Double rate;  // Giá điện (VNĐ/kWh)

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;  // Thời gian tạo bản ghi

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;  // Thời gian cập nhật bản ghi
}
