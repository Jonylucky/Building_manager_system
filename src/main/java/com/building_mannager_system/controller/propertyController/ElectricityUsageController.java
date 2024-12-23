package com.building_mannager_system.controller.propertyController;

import com.building_mannager_system.dto.requestDto.propertyDto.ElectricityUsageDTO;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.service.system_service.ElectricityUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/electricity-usage")
public class ElectricityUsageController {
    @Autowired
    private ElectricityUsageService electricityUsageService;

    @PostMapping
    public ResponseEntity<ApiResponce<ElectricityUsageDTO>> createElectricityUsage(@RequestBody ElectricityUsageDTO dto) {
        // Tạo chỉ số điện mới
        ElectricityUsageDTO createdUsage = electricityUsageService.createElectricityUsage(dto);

        // Tạo ApiResponse với dữ liệu trả về
        ApiResponce<ElectricityUsageDTO> apiResponse = new ApiResponce<>(HttpStatus.CREATED.value(), createdUsage, "success");

        // Trả về ApiResponse với mã trạng thái và dữ liệu
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
