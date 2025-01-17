package com.building_mannager_system.controller.propertyController;

import com.building_mannager_system.dto.requestDto.propertyDto.DeviceTypeDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.entity.property_manager.DeviceType;
import com.building_mannager_system.service.property_manager.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device-types")
public class DeviceTypeController {
    @Autowired
    private DeviceTypeService deviceTypeService;

    // Create a new DeviceType
    // Create a new DeviceType
    @PostMapping
    public ResponseEntity<ApiResponce<DeviceTypeDto>> createDeviceType(@RequestBody DeviceTypeDto deviceTypeDto) {
        DeviceTypeDto createdDeviceType = deviceTypeService.createDeviceType(deviceTypeDto);
        ApiResponce<DeviceTypeDto> response = new ApiResponce<>(HttpStatus.CREATED.value(), createdDeviceType, "DeviceType created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all DeviceTypes
    @GetMapping
    public ResponseEntity<ApiResponce<List<DeviceType>>> getAllDeviceTypes() {
        List<DeviceType> deviceTypes = deviceTypeService.getAllDeviceTypes();
        ApiResponce<List<DeviceType>> response = new ApiResponce<>(HttpStatus.OK.value(), deviceTypes, "DeviceTypes fetched successfully");
        return ResponseEntity.ok(response);
    }

    // Get a DeviceType by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponce<DeviceTypeDto>> getDeviceTypeById(@PathVariable int id) {
        DeviceTypeDto deviceType = deviceTypeService.getDeviceTypeById(id);
        ApiResponce<DeviceTypeDto> response = new ApiResponce<>(HttpStatus.OK.value(), deviceType, "DeviceType fetched successfully");
        return ResponseEntity.ok(response);
    }

    // Update a DeviceType
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce<DeviceTypeDto>> updateDeviceType(@PathVariable int id, @RequestBody DeviceTypeDto deviceTypeDto) {
        DeviceTypeDto updatedDeviceType = deviceTypeService.updateDeviceType(id, deviceTypeDto);
        ApiResponce<DeviceTypeDto> response = new ApiResponce<>(HttpStatus.OK.value(), updatedDeviceType, "DeviceType updated successfully");
        return ResponseEntity.ok(response);
    }

    // Delete a DeviceType
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponce<Void>> deleteDeviceType(@PathVariable int id) {
        deviceTypeService.deleteDeviceType(id);
        ApiResponce<Void> response = new ApiResponce<>(HttpStatus.NO_CONTENT.value(), null, "DeviceType deleted successfully");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
