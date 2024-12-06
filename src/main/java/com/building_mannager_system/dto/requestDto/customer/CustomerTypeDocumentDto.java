package com.building_mannager_system.dto.requestDto.customer;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerTypeDocumentDto {
    private Integer id;  // ID của tài liệu
    private String documentType;  // Loại tài liệu
    private boolean status;  // Trạng thái tài liệu
    private Integer customerTypeId;  // ID của CustomerType
}
