package com.building_mannager_system.controller.propertyController;

import com.building_mannager_system.dto.requestDto.propertyDto.SystemMaintenanceServiceDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.entity.property_manager.SystemMaintenanceService;
import com.building_mannager_system.service.property_manager.SystemMaintenanceService_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/systemMainteance")
public class SystemMaintenanceService_ServieController {
    @Autowired
    private SystemMaintenanceService_Service systemMaintenanceService;

    // Create a new System Maintenance record
    @PostMapping
    public ResponseEntity<ApiResponce<SystemMaintenanceServiceDto>> createSystemMaintenance(@RequestBody SystemMaintenanceServiceDto systemMaintenanceDto) {
        SystemMaintenanceServiceDto createdRecord = systemMaintenanceService.createService(systemMaintenanceDto);
        ApiResponce<SystemMaintenanceServiceDto> response = new ApiResponce<>(200, createdRecord, "System maintenance created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all System Maintenance records
    @GetMapping
    public ResponseEntity<ApiResponce<List<SystemMaintenanceServiceDto>>> getAllSystemMaintenances() {
        List<SystemMaintenanceServiceDto> records = systemMaintenanceService.getAllSystemMaintenances();
        ApiResponce<List<SystemMaintenanceServiceDto>> response = new ApiResponce<>(200, records, "Success");
        return ResponseEntity.ok(response);
    }

    // Get a System Maintenance record by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponce<SystemMaintenanceServiceDto>> getSystemMaintenanceById(@PathVariable int id) {
        SystemMaintenanceServiceDto record = systemMaintenanceService.findServiceById(id);
        if (record != null) {
            ApiResponce<SystemMaintenanceServiceDto> response = new ApiResponce<>(200, record, "System maintenance found");
            return ResponseEntity.ok(response);
        } else {
            ApiResponce<SystemMaintenanceServiceDto> response = new ApiResponce<>(404, null, "System maintenance not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Update a System Maintenance record by ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce<SystemMaintenanceServiceDto>> updateSystemMaintenance(@PathVariable int id, @RequestBody SystemMaintenanceServiceDto systemMaintenanceDto) {
        SystemMaintenanceServiceDto updatedRecord = systemMaintenanceService.updateService(id, systemMaintenanceDto);
        if (updatedRecord != null) {
            ApiResponce<SystemMaintenanceServiceDto> response = new ApiResponce<>(200, updatedRecord, "System maintenance updated successfully");
            return ResponseEntity.ok(response);
        } else {
            ApiResponce<SystemMaintenanceServiceDto> response = new ApiResponce<>(404, null, "System maintenance not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete a System Maintenance record by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponce<Void>> deleteSystemMaintenance(@PathVariable int id) {
        boolean isDeleted = systemMaintenanceService.deleteSystemMaintenance(id);
        if (isDeleted) {
            ApiResponce<Void> response = new ApiResponce<>(200, null, "System maintenance deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            ApiResponce<Void> response = new ApiResponce<>(404, null, "System maintenance not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
