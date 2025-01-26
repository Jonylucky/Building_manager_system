package com.building_mannager_system.entity.property_manager;


import com.building_mannager_system.enums.ResultStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "check_result") // Tên bảng trong cơ sở dữ liệu
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Liên kết tới `ItemCheck`
    @JoinColumn(name = "check_item_id", nullable = false) // Khóa ngoại liên kết với bảng `item_check`
    private ItemCheck itemCheck;

    @Enumerated(EnumType.STRING) // Enum cho kết quả kiểm tra
    @Column(name = "result", nullable = false, length = 50)
    private ResultStatus result; // Đạt, Không đạt, Cần sửa chữa

    @Column(name = "note", columnDefinition = "TEXT") // Ghi chú bổ sung
    private String note;

    @Column(name = "technichan_name")
    private String technichanName;
    @Column(name = "checked_at", nullable = false) // Thời gian kiểm tra
    private LocalDateTime checkedAt;
}

