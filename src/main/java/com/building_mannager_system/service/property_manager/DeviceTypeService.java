package com.building_mannager_system.service.property_manager;

import com.building_mannager_system.dto.requestDto.propertyDto.DeviceTypeDto;
import com.building_mannager_system.entity.property_manager.DeviceType;
import com.building_mannager_system.mapper.propertiMapper.DeviceTypeMapper;
import com.building_mannager_system.repository.system_manager.DeviceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceTypeService {
    // Create a new DeviceType
    @Autowired
    private DeviceTypeRepository deviceTypeRepository;
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;
    public DeviceTypeDto createDeviceType(DeviceTypeDto deviceTypeDTO) {
        DeviceType deviceType = deviceTypeMapper.toEntity(deviceTypeDTO);
        DeviceType savedDeviceType = deviceTypeRepository.save(deviceType);
        return deviceTypeMapper.toDTO(savedDeviceType);
    }

    // Read all DeviceTypes
    public List<DeviceType> getAllDeviceTypes() {
        return deviceTypeRepository.findAll();

    }

    // Read a DeviceType by ID
    public DeviceTypeDto getDeviceTypeById(int id) {
        Optional<DeviceType> deviceType = deviceTypeRepository.findById(id);
        return deviceType.map(deviceTypeMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("DeviceType not found with ID: " + id));
    }

    // Update a DeviceType
    public DeviceTypeDto updateDeviceType(int id, DeviceTypeDto deviceTypeDTO) {
        DeviceType existingDeviceType = deviceTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DeviceType not found with ID: " + id));

        existingDeviceType.setTypeName(deviceTypeDTO.getTypeName());
        existingDeviceType.setDescription(deviceTypeDTO.getDescription());

        DeviceType updatedDeviceType = deviceTypeRepository.save(existingDeviceType);
        return deviceTypeMapper.toDTO(updatedDeviceType);
    }

    // Delete a DeviceType
    public void deleteDeviceType(int id) {
        if (!deviceTypeRepository.existsById(id)) {
            throw new RuntimeException("DeviceType not found with ID: " + id);
        }
        deviceTypeRepository.deleteById(id);
    }

}
