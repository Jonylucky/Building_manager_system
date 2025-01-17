package com.building_mannager_system.dto.requestDto.work_registration;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WorkRegistrationDto {
    private Long registrationID;
    private Long accountId;
    private String accountUsername;
    private LocalDateTime registrationDate;
    private LocalDateTime scheduledDate;
    private String status;
    private String note;
    private String constructionDrawingUrl; // Bản vẽ thi công
}
