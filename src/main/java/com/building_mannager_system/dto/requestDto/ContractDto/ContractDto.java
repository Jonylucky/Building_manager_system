package com.building_mannager_system.dto.requestDto.ContractDto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private String leaseStatus;
    private int officeId;
   private int customerId;
   private BigDecimal totalAmount;
   private  String fileName;


}
