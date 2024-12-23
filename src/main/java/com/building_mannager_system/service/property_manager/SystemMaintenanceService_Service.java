package com.building_mannager_system.service.property_manager;


import com.building_mannager_system.dto.requestDto.propertyDto.SystemMaintenanceServiceDto;
import com.building_mannager_system.entity.property_manager.SystemMaintenanceService;
import com.building_mannager_system.mapper.propertiMapper.SystemMaintenanceServiceMapper;
import com.building_mannager_system.repository.system_manager.SystemMaintenanceServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemMaintenanceService_Service {
    @Autowired
    private SystemMaintenanceServiceRepository repository;

    @Autowired
    private SystemMaintenanceServiceMapper mapper;

    public SystemMaintenanceServiceDto findServiceById(int id) {
        SystemMaintenanceService service = repository.findById(id).orElse(null);
        return mapper.toDto(service);
    }
    public SystemMaintenanceServiceDto createService(SystemMaintenanceServiceDto serviceDto) {
        SystemMaintenanceService service = mapper.toEntity(serviceDto);
        SystemMaintenanceService savedService = repository.save(service);
        return mapper.toDto(savedService);
    }
    public SystemMaintenanceServiceDto updateService(int id, SystemMaintenanceServiceDto serviceDto) {
        Optional<SystemMaintenanceService> optionalService = repository.findById(id);
        if (optionalService.isPresent()) {
            SystemMaintenanceService service = optionalService.get();
            // Update fields in the service entity based on the DTO
            // ...
            SystemMaintenanceService updatedService = repository.save(service);
            return mapper.toDto(updatedService);
        } else {
            // Handle not found scenario
            throw new EntityNotFoundException("Service not found");
        }
    }
    // Delete
    public boolean deleteSystemMaintenance(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // Read all
    public List<SystemMaintenanceServiceDto> getAllSystemMaintenances() {
        List<SystemMaintenanceService> entities = repository.findAll();
        return mapper.toDtoList(entities);
    }
}
