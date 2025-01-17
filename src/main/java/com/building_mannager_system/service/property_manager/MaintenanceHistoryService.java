package com.building_mannager_system.service.property_manager;

import com.building_mannager_system.dto.requestDto.propertyDto.MaintenanceHistoryDto;
import com.building_mannager_system.entity.property_manager.MaintenanceHistory;
import com.building_mannager_system.mapper.propertiMapper.MaintenanceHistoryMapper;
import com.building_mannager_system.repository.system_manager.MaintenanceHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceHistoryService {
    @Autowired
    private MaintenanceHistoryRepository repository;

    @Autowired
    private MaintenanceHistoryMapper mapper;

    public MaintenanceHistory createHistory(MaintenanceHistoryDto historyDto) {
        // Validate the incoming DTO
        if (historyDto == null) {
            throw new IllegalArgumentException("MaintenanceHistoryDto cannot be null");
        }

        if (historyDto.getDeviceId() == null) {
            throw new IllegalArgumentException("DeviceId is required");
        }

        if (historyDto.getMaintenanceId() == null) {
            throw new IllegalArgumentException("MaintenanceId is required");
        }

        // Map DTO to Entity
        MaintenanceHistory maintenanceHistory = mapper.toEntity(historyDto);

        // Debug log for mapped entity
        System.out.println("Mapped MaintenanceHistory: " + maintenanceHistory);

        // Validate the mapped entity
        if (maintenanceHistory.getDevice() == null) {
            throw new IllegalStateException("Device mapping failed. Device cannot be null.");
        }

        if (maintenanceHistory.getMaintenanceService() == null) {
            throw new IllegalStateException("MaintenanceService mapping failed. MaintenanceService cannot be null.");
        }

        // Save the entity to the database
        MaintenanceHistory savedHistory = repository.save(maintenanceHistory);

        // Log the saved entity
        System.out.println("Saved MaintenanceHistory: " + savedHistory);

        return savedHistory;
    }

    // Get all Maintenance Histories
    public List<MaintenanceHistoryDto> getAllMaintenanceHistories() {
        List<MaintenanceHistory> histories = repository.findAll();
        return mapper.toDtoList(histories);
    }
    public MaintenanceHistoryDto findHistoryById(int id) {
        Optional<MaintenanceHistory> optionalHistory = repository.findById(id);
        return optionalHistory.map(mapper::toDto).orElse(null);
    }
    public MaintenanceHistoryDto updateHistory(int id, MaintenanceHistoryDto historyDto) {
        Optional<MaintenanceHistory> optionalHistory = repository.findById(id);
        if (optionalHistory.isPresent()) {
            MaintenanceHistory history = optionalHistory.get();
            // Update specific fields in the history entity based on the DTO
            history.setNotes(historyDto.getNotes());  // Example for updating notes
            // ... update other fields as needed
            MaintenanceHistory updatedHistory = repository.save(history);
            return mapper.toDto(updatedHistory);
        } else {
            // Handle not found scenario
            throw new EntityNotFoundException("History not found");
        }
    }
    // Delete Maintenance History
    public boolean deleteMaintenanceHistory(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
