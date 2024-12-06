package com.building_mannager_system.controller.officeSapceAllcation;

import com.building_mannager_system.dto.requestDto.oficeSapceAllcationDto.OfficesDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.entity.customer_service.contact_manager.Office;
import com.building_mannager_system.service.officeAllcation.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/offices")
public class OfficeController {
    @Autowired
    private OfficeService officeService;

    /**
     * Endpoint to create a new office
     */
    @PostMapping("/create")
    public ResponseEntity<ApiResponce<Office>> createOffice(@RequestBody OfficesDto officeDto) {
        try {
            Office createdOffice = officeService.createOffice(officeDto);
            return new ResponseEntity<>(new ApiResponce<>(200, createdOffice, "Office created successfully"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponce<>(500, null, "Failed to create office: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
