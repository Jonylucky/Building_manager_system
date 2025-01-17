package com.building_mannager_system.controller.propertyController;


import com.building_mannager_system.dto.requestDto.propertyDto.MaintenanceHistoryDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.entity.property_manager.MaintenanceHistory;
import com.building_mannager_system.service.property_manager.MaintenanceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/maintenance_history")
public class MaintenancceHistoryController {
    @Autowired
    private MaintenanceHistoryService maintenanceHistoryService;
    // Create a new maintenance history record
    @PostMapping
    public ResponseEntity<ApiResponce<MaintenanceHistory>> createMaintenanceHistory(@RequestBody MaintenanceHistoryDto maintenanceHistoryDto) {
        MaintenanceHistory createdHistory = maintenanceHistoryService.createHistory(maintenanceHistoryDto);
        ApiResponce<MaintenanceHistory> response = new ApiResponce<>(200, createdHistory, "Success");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all maintenance history records
    @GetMapping
    public ResponseEntity<ApiResponce<List<MaintenanceHistoryDto>>> getAllMaintenanceHistories() {
        List<MaintenanceHistoryDto> histories = maintenanceHistoryService.getAllMaintenanceHistories();
        ApiResponce<List<MaintenanceHistoryDto>> response = new ApiResponce<>(200, histories, "Success");
        return ResponseEntity.ok(response);
    }

    // Get maintenance history by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponce<MaintenanceHistoryDto>> getMaintenanceHistoryById(@PathVariable int id) {
        MaintenanceHistoryDto history = maintenanceHistoryService.findHistoryById(id);
        if (history != null) {
            ApiResponce<MaintenanceHistoryDto> response = new ApiResponce<>(200, history, "Success");
            return ResponseEntity.ok(response);
        } else {
            ApiResponce<MaintenanceHistoryDto> response = new ApiResponce<>(404, null, "Maintenance History not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Update a maintenance history record
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce<MaintenanceHistoryDto>> updateMaintenanceHistory(
            @PathVariable int id,
            @RequestBody  MaintenanceHistoryDto maintenanceHistoryDto) {
        MaintenanceHistoryDto updatedHistory = maintenanceHistoryService.updateHistory(id, maintenanceHistoryDto);
        if (updatedHistory != null) {
            ApiResponce<MaintenanceHistoryDto> response = new ApiResponce<>(200, updatedHistory, "Success");
            return ResponseEntity.ok(response);
        } else {
            ApiResponce<MaintenanceHistoryDto> response = new ApiResponce<>(404, null, "Maintenance History not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete a maintenance history record
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponce<Void>> deleteMaintenanceHistory(@PathVariable int id) {
        boolean isDeleted = maintenanceHistoryService.deleteMaintenanceHistory(id);
        if (isDeleted) {
            ApiResponce<Void> response = new ApiResponce<>(200, null, "Maintenance History deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            ApiResponce<Void> response = new ApiResponce<>(404, null, "Maintenance History not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
