package com.building_mannager_system.dto.requestDto.propertyDto;

import com.building_mannager_system.enums.QuotationStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class QuotationDto {
    private Long id;  // Mã báo giá

    private String supplierName;  // Tên nhà cung cấp

    private BigDecimal totalAmount;  // Tổng số tiền báo giá

    private String details;  // Chi tiết các hạng mục báo giá

    private String fileName;  // Tên file báo giá (nếu có)

    private QuotationStatus status;  // Trạng thái báo giá (Pending, Approved, Rejected)
}
