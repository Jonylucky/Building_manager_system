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

    public MaintenanceHistoryDto createHistory(MaintenanceHistoryDto historyDto) {
        MaintenanceHistory history = mapper.toEntity(historyDto);
        return mapper.toDto(repository.save(history));
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
