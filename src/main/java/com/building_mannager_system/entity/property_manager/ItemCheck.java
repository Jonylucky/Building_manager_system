package com.building_mannager_system.entity.property_manager;


import com.building_mannager_system.enums.Frequency;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_check") // Tên bảng trong cơ sở dữ liệu
public class ItemCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Liên kết đến bảng `device`
    @JoinColumn(name = "device_id", nullable = false) // Cột khóa ngoại
    private Device device;

    @Column(name = "check_category", nullable = false, length = 255) // Danh mục kiểm tra
    private String checkCategory;

    @Column(name = "check_name", nullable = false, columnDefinition = "TEXT") // Tên mục kiểm tra
    private String checkName;

    @Column(name = "standard", length = 255) // Tiêu chuẩn kiểm tra
    private String standard;

    @Enumerated(EnumType.STRING) // Liên kết với enum tần suất kiểm tra
    @Column(name = "frequency", length = 50)
    private Frequency frequency; // Enum cho tần suất kiểm tra (Hàng ngày, Hàng tháng, ...)
}
