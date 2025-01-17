package com.building_mannager_system.dto.requestDto.work_registration;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RepairRequestDto {
    private Long requestID;
    private Long accountId;
    private String accountUsername;
    private LocalDateTime requestDate;
    private String content;
    private String status;
    private String imageUrl;
}
