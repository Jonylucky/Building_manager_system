package com.building_mannager_system.service.property_manager;

import com.building_mannager_system.dto.requestDto.propertyDto.DeviceDto;
import com.building_mannager_system.entity.property_manager.Device;
import com.building_mannager_system.mapper.propertiMapper.DeviceMapper;
import com.building_mannager_system.repository.system_manager.DeviceRepository;
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
    public DeviceDto findDeviceById(int id) {
        Optional<Device> optionalDevice = repository.findById(id);
        return optionalDevice.map(mapper::toDto).orElse(null);
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
}
