package com.building_mannager_system.controller.propertyController;

import com.building_mannager_system.dto.requestDto.propertyDto.DeviceDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.service.property_manager.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    // Create a new device
    @PostMapping
    public ResponseEntity<ApiResponce<DeviceDto>> createDevice(@RequestBody DeviceDto deviceDto) {
        DeviceDto createdDevice = deviceService.createDevice(deviceDto);
        ApiResponce<DeviceDto> response = new ApiResponce<>(200, createdDevice, "Device created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all devices
    @GetMapping
    public ResponseEntity<ApiResponce<List<DeviceDto>>> getAllDevices() {
        List<DeviceDto> devices = deviceService.getAllDevices();
        ApiResponce<List<DeviceDto>> response = new ApiResponce<>(200, devices, "Success");
        return ResponseEntity.ok(response);
    }

    // Get a device by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponce<DeviceDto>> getDeviceById(@PathVariable int id) {
        DeviceDto device = deviceService.findDeviceById(id);
        if (device != null) {
            ApiResponce<DeviceDto> response = new ApiResponce<>(200, device, "Device found");
            return ResponseEntity.ok(response);
        } else {
            ApiResponce<DeviceDto> response = new ApiResponce<>(404, null, "Device not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Update a device by ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce<DeviceDto>> updateDevice(@PathVariable int id, @RequestBody DeviceDto deviceDto) {
        DeviceDto updatedDevice = deviceService.updateDevice(id, deviceDto);
        if (updatedDevice != null) {
            ApiResponce<DeviceDto> response = new ApiResponce<>(200, updatedDevice, "Device updated successfully");
            return ResponseEntity.ok(response);
        } else {
            ApiResponce<DeviceDto> response = new ApiResponce<>(404, null, "Device not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete a device by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponce<Void>> deleteDevice(@PathVariable int id) {
        boolean isDeleted = deviceService.deleteDevice(id);
        if (isDeleted) {
            ApiResponce<Void> response = new ApiResponce<>(200, null, "Device deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            ApiResponce<Void> response = new ApiResponce<>(404, null, "Device not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
