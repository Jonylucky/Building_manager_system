package com.building_mannager_system.service.property_manager;

import com.building_mannager_system.dto.requestDto.systemDto.SystemDto;
import com.building_mannager_system.entity.property_manager.Systems;
import com.building_mannager_system.mapper.propertiMapper.SystemMapper;
import com.building_mannager_system.repository.system_manager.SystemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemsService {

    @Autowired
    private SystemsRepository systemsRepository;
    @Autowired
    private SystemMapper systemMapper;

    // Create System
    public SystemDto createSystem(SystemDto systemDto) {
        Systems system = systemMapper.toEntity(systemDto);
        Systems savedSystem = systemsRepository.save(system);
        return systemMapper.toDTO(savedSystem);
    }

    // Get All Systems
    public List<SystemDto> getAllSystems() {
        List<Systems> systems = systemsRepository.findAll();
        return systems.stream()
                .map(systemMapper::toDTO)
                .toList();
    }

    // Get System by ID
    public SystemDto getSystemById(Long id) {
        return systemsRepository.findById(id)
                .map(systemMapper::toDTO)
                .orElse(null);
    }

    // Update System
    public SystemDto updateSystem(Long id, SystemDto systemDto) {
        Systems existingSystem = systemsRepository.findById(id)
                .orElse(null);

        // Update fields using DTO
        if (existingSystem != null) {
            existingSystem.setSystemName(systemDto.getSystemName());
            existingSystem.setDescription(systemDto.getDescription());

            Systems updatedSystem = systemsRepository.save(existingSystem);
            return systemMapper.toDTO(updatedSystem);
        }

       return null;
    }

    public boolean deleteSystem(Long id) {
        if (systemsRepository.existsById(id)) {
            systemsRepository.deleteById(id);
            return true; // Xóa thành công
        }
        return false; // Không tìm thấy hệ thống
    }




}
