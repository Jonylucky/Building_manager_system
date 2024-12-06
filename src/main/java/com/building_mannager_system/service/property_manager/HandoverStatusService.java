package com.building_mannager_system.service.property_manager;

import com.building_mannager_system.dto.requestDto.propertyDto.HandoverStatusDto;
import com.building_mannager_system.entity.customer_service.contact_manager.HandoverStatus;
import com.building_mannager_system.mapper.propertiMapper.HandoverStatusMapper;
import com.building_mannager_system.repository.Contract.HandoverStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HandoverStatusService {
    @Autowired
    private HandoverStatusRepository handoverStatusRepository;
    @Autowired
    private HandoverStatusMapper handoverStatusMapper;
    // Create or Update a Handover Status
    public HandoverStatusDto saveOrUpdateHandover(HandoverStatusDto handoverDto) {
        if (handoverDto.getOfficeId() == null) {
            throw new RuntimeException("Office ID cannot be null");
        }

        HandoverStatus handover = handoverStatusMapper.toEntity( handoverDto); // Convert DTO to Entity
        HandoverStatus savedHandover = handoverStatusRepository.save(handover); // Save or Update
        return handoverStatusMapper.toDto(savedHandover); // Convert back to DTO
    }

    // Get a Handover Status by ID
    public HandoverStatusDto getHandoverById(Integer id) {
        HandoverStatus handover = handoverStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Handover not found with ID: " + id));
        return handoverStatusMapper.toDto(handover);
    }

    // Delete a Handover Status by ID
    public void deleteHandoverById(Integer id) {
        if (!handoverStatusRepository.existsById(id)) {
            throw new RuntimeException("Handover not found with ID: " + id);
        }
        handoverStatusRepository.deleteById(id);
    }

    // Get All Handover Statuses
    public List<HandoverStatusDto> getAllHandovers() {
        List<HandoverStatus> handovers = handoverStatusRepository.findAll();
        return handovers.stream()
                .map(handoverStatusMapper::toDto) // Map each entity to DTO
                .collect(Collectors.toList());
    }
    public void deleteHandover(Integer id) {
        if (handoverStatusRepository.existsById(id)) {
            handoverStatusRepository.deleteById(id); // Delete by ID
        } else {
            throw new IllegalArgumentException("Handover with ID " + id + " does not exist");
        }
    }

}
