package com.building_mannager_system.entity.notification;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name =  "Recipient")

public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // ID tự động tăng, duy nhất cho mỗi Recipient

    @Column(nullable = false)
    private String type; // Loại đối tượng (VD: "Contact", "Office", "Customer"...)

    @Column(nullable = false)
    private Integer referenceId; // ID tham chiếu đến đối tượng cụ thể (VD: contactId, officeId)

    @Column(nullable = false)
    private String name; // Tên của đối tượng (nếu cần để hiển thị thông tin bổ sung)

    // Thời gian tạo bản ghi
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
