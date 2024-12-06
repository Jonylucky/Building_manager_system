package com.building_mannager_system.entity.customer_service.customer_manager;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE)
@Table(name = "customertypedocument")
public class CustomertypeDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Đảm bảo tự động tăng
    @Column(name = "CustomerDocumentId", nullable = false)
    private Integer Id;  // Khóa chính tự động tăng



    @Column(name = "DocumentType", nullable = false)
    private String documentType;  // Loại tài liệu

    @Column(name = "Status", nullable = false)
    private boolean status;  // Trạng thái (hoạt động hoặc không)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerTypeId", nullable = false)
    private Customertype customerType;  // Liên kết với CustomerType
}
