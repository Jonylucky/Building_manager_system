package com.building_mannager_system.controller.propertyController;

import com.building_mannager_system.dto.requestDto.propertyDto.ElectricityUsageDTO;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.entity.customer_service.system_manger.ElectricityUsage;
import com.building_mannager_system.service.system_service.ElectricityUsageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/usage-history")
    public ResponseEntity<List<ElectricityUsageDTO>> getElectricityUsageHistory(@RequestParam int meterId) {
        ElectricityUsageDTO dto = new ElectricityUsageDTO();
        dto.setMeterId(meterId);

        List<ElectricityUsageDTO> usageHistory = electricityUsageService.getElectricityUsageByMeterAndDate(dto);

        return ResponseEntity.ok(usageHistory);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteElectricityUsage(@PathVariable int id) {
        try {
            electricityUsageService.deleteElectricityUsage(id);
            return ResponseEntity.ok("Đã xóa bản ghi với ID: " + id);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
