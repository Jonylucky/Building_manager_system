package com.building_mannager_system.controller.propertyController;

import com.building_mannager_system.dto.requestDto.propertyDto.*;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.entity.property_manager.Device;
import com.building_mannager_system.mapper.propertiMapper.DeviceMapper;
import com.building_mannager_system.mapper.propertiMapper.MaintenanceHistoryMapper;
import com.building_mannager_system.mapper.propertiMapper.RiskAssessmentMapper;
import com.building_mannager_system.service.property_manager.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private MaintenanceHistoryMapper maintenanceHistoryMapper;
    @Autowired
    private RiskAssessmentMapper riskAssessmentMapper;
    @Autowired
    private DeviceMapper deviceMapper;
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

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponce<DeviceDetailDto>> getDeviceById(@PathVariable int id) {
        Device device = deviceService.findDeviceById(id);

        if (device != null) {
            // Map the device entity to the DeviceDetailDto using the mapper
            DeviceDetailDto deviceDto = deviceMapper.toDetailDto(device);

            // Create and return a successful response
            ApiResponce<DeviceDetailDto> response = new ApiResponce<>(200, deviceDto, "Device found");
            return ResponseEntity.ok(response);
        } else {
            // Create and return a NOT_FOUND response
            ApiResponce<DeviceDetailDto> response = new ApiResponce<>(404, null, "Device not found");
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
    @PostMapping("/{deviceId}/risk-assessments-and-maintenance-history")
    public ResponseEntity<ApiResponce<DeviceDto>> addRiskAssessmentAndMaintenanceHistoryToDevice(
            @PathVariable int deviceId,
            @RequestBody AddRiskAssessmentAndMaintenanceHistoryRequestDto request) {

        DeviceDto updatedDevice = deviceService.addRiskAssessmentAndMaintenanceHistoryToDevice(
                deviceId, request.getRiskAssessmentDto(), request.getMaintenanceHistoryDto());

        ApiResponce<DeviceDto> response = new ApiResponce<>(200, updatedDevice, "Risk Assessment and Maintenance History added successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
