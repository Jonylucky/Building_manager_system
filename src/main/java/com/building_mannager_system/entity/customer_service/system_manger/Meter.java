package com.building_mannager_system.entity.customer_service.system_manger;

import com.building_mannager_system.entity.customer_service.contact_manager.Office;
import com.building_mannager_system.entity.customer_service.officeSpaceAllcation.Location;
import com.building_mannager_system.enums.MeterType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Meter")

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MeterId", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OfficeId", nullable = false)
    private Office office;  // Liên kết đến văn phòng

    @Column(name = "SerialNumber", nullable = false)
    private String serialNumber;  // Số seri của đồng hồ

    @Enumerated(EnumType.STRING)
    @Column(name = "MeterType", nullable = false)
    private MeterType meterType;  // Loại đồng hồ (một pha hoặc ba pha)

    @Column(name = "InstallationDate")  // Đổi tên trường thành "InstallationDate"
    private LocalDate installationDate;  // Ngày lắp

    @OneToMany(mappedBy = "meter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElectricityUsage> electricityUsages = new ArrayList<>();  // Lịch sử sử dụng điện

}
