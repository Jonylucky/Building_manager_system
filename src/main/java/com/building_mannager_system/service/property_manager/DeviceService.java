package com.building_mannager_system.service.property_manager;

import com.building_mannager_system.dto.requestDto.propertyDto.DeviceDto;
import com.building_mannager_system.dto.requestDto.propertyDto.MaintenanceHistoryDto;
import com.building_mannager_system.dto.requestDto.propertyDto.RiskAssessmentDto;
import com.building_mannager_system.entity.property_manager.Device;
import com.building_mannager_system.entity.property_manager.MaintenanceHistory;
import com.building_mannager_system.entity.property_manager.RiskAssessment;
import com.building_mannager_system.entity.property_manager.SystemMaintenanceService;
import com.building_mannager_system.mapper.propertiMapper.DeviceMapper;
import com.building_mannager_system.mapper.propertiMapper.MaintenanceHistoryMapper;
import com.building_mannager_system.mapper.propertiMapper.RiskAssessmentMapper;
import com.building_mannager_system.repository.system_manager.DeviceRepository;
import com.building_mannager_system.repository.system_manager.SystemMaintenanceServiceRepository;
import com.building_mannager_system.service.system_service.RiskAssessmentServcie;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository repository;

    @Autowired
    private DeviceMapper mapper;
    @Autowired
    private RiskAssessmentServcie riskAssessmentServcie;
    @Autowired
    private MaintenanceHistoryService maintenanceHistoryService;
    @Autowired
    private RiskAssessmentMapper riskAssessmentMapper;
    @Autowired
    private SystemMaintenanceServiceRepository systemMaintenanceServiceRepository;

    @Autowired
    private MaintenanceHistoryMapper maintenanceHistoryMapper;

    public DeviceDto createDevice(DeviceDto deviceDto) {
        Device device = mapper.toEntity(deviceDto);
        Device savedDevice = repository.save(device);
        return mapper.toDto(savedDevice);
    }

    // Get all devices
    public List<DeviceDto> getAllDevices() {
        List<Device> devices = repository.findAll();
        return mapper.toDtoList(devices);
    }
    public Device findDeviceById(int id) {
        Optional<Device> optionalDevice = repository.findById(id);
        return  optionalDevice.orElse(null);
    }

    public DeviceDto updateDevice(int id, DeviceDto deviceDto) {
        Optional<Device> optionalDevice = repository.findById(id);
        if (optionalDevice.isPresent()) {
            Device device = optionalDevice.get();
            // Update specific fields in the device entity based on the DTO
            device.setDeviceName(deviceDto.getDeviceName());  // Example for updating deviceName

            // ... update other fields as needed
            Device updatedDevice = repository.save(device);
            return mapper.toDto(updatedDevice);
        } else {
            // Handle not found scenario
            throw new EntityNotFoundException("Device not found");
        }
    }
    // Delete a device by ID
    public boolean deleteDevice(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    public DeviceDto addRiskAssessmentAndMaintenanceHistoryToDevice(int deviceId, RiskAssessmentDto riskAssessmentDto, MaintenanceHistoryDto maintenanceHistoryDto) {
        // Bước 1: Tìm Device theo ID
        Device device = repository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found with ID: " + deviceId));


        maintenanceHistoryDto.setDeviceId((long) deviceId);


        // Lưu MaintenanceHistory vào cơ sở dữ liệu
        MaintenanceHistory savedMaintenanceHistory = maintenanceHistoryService.createHistory(maintenanceHistoryDto);

        riskAssessmentDto.setMaintenanceID(savedMaintenanceHistory.getId().intValue());

        // Bước 4: Tạo RiskAssessment từ RiskAssessmentDto
        RiskAssessment riskAssessment = riskAssessmentMapper.toEntity(riskAssessmentDto);
        riskAssessment.setDevice(device); // Gắn với Device
        riskAssessment.setMaintenanceHistory(savedMaintenanceHistory); // Gắn với MaintenanceHistory

        // Lưu RiskAssessment vào cơ sở dữ liệu
        RiskAssessment savedRiskAssessment = riskAssessmentServcie.createRiskAssessment(riskAssessment);

        // Bước 5: Cập nhật Device với MaintenanceHistory và RiskAssessment mới
        device.getMaintenanceHistories().add(savedMaintenanceHistory);
        device.getRiskAssessments().add(savedRiskAssessment);

        // Lưu lại Device với các bản ghi mới
        repository.save(device);

        // Bước 6: Chuyển Device sang DTO và trả về
        return mapper.toDto(device);
    }
}
