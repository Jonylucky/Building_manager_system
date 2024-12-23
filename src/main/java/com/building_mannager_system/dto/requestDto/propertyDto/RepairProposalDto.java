package com.building_mannager_system.dto.requestDto.propertyDto;

import com.building_mannager_system.entity.property_manager.Quotation;
import com.building_mannager_system.enums.ProposalStatus;
import com.building_mannager_system.enums.ProposalType;
import com.building_mannager_system.enums.QuotationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class RepairProposalDto {
    private Long id;  // Mã đề xuất

    private String title;  // Tiêu đề của đề xuất

    private String description;  // Mô tả chi tiết của đề xuất

    private LocalDate requestDate;  // Ngày yêu cầu

    private Integer priority;  // Mức độ ưu tiên của đề xuất

    private ProposalType proposalType;  // Loại đề xuất (RiskAssessment hoặc Sự cố bất thường)

    private Long riskAssessmentId;  // ID của RiskAssessment nếu có

    private List<Quotation> quotations;  // Danh sách báo giá

    private ProposalStatus status;  // Trạng thái của đề xuất (Pending, Approved, Rejected, Completed)
}
