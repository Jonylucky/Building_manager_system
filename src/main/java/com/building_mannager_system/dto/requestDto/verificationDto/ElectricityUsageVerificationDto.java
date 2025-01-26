package com.building_mannager_system.dto.requestDto.verificationDto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ElectricityUsageVerificationDto {
    private Integer id;  // ID tự động tăng

    private Integer meterId;  // ID của Meter (đồng hồ điện)

    private BigDecimal startReading;  // Chỉ số đầu kỳ (tháng trước)

    private BigDecimal endReading;  // Chỉ số cuối kỳ (tháng hiện tại)

    private LocalDate readingDate;  // Ngày ghi chỉ số điện


    private String imageName;  // Tên hình ảnh của chỉ số điện tháng hiện tại


    private String previousMonthImageName;  // Tên hình ảnh của chỉ số điện tháng trước

    private String status;  // Trạng thái (đã xác nhận hoặc chưa)

    private BigDecimal previousMonthCost;  // Số tiền của tháng trước

    private BigDecimal usageAmountPreviousMonth;  // Lượng điện sử dụng tháng trước

    private BigDecimal usageAmountCurrentMonth;  // Lượng điện sử dụng tháng hiện tại

    private BigDecimal currentMonthCost;  // Số tiền của tháng hiện tại
}
