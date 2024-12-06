package com.building_mannager_system.dto.requestDto.customer;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerDto {
    private Integer id;
    private String companyName;
    private Integer customerTypeId;  // This will store the customerType ID
    private String email;
    private String phone;
    private String address;
    private String status;
    private String directorName;
    private LocalDate birthday;
}
