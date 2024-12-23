package com.building_mannager_system.service.system_service;

import com.building_mannager_system.dto.requestDto.propertyDto.RiskAssessmentDto;
import com.building_mannager_system.entity.property_manager.RiskAssessment;
import com.building_mannager_system.mapper.propertiMapper.RiskAssessmentMapper;
import com.building_mannager_system.repository.system_manager.RiskAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RiskAssessmentServcie {

    @Autowired
    private RiskAssessmentRepository repository;
    @Autowired
    private RiskAssessmentMapper mapper;
    // Create RiskAssessment
    public RiskAssessmentDto createRiskAssessment(RiskAssessmentDto riskAssessmentDto) {
        RiskAssessment entity = mapper.toEntity(riskAssessmentDto);
        // Tính toán RiskPriorityNumber (RPN)
        int rpn = entity.getRiskProbability() * entity.getRiskImpact() * entity.getRiskDetection();
        entity.setRiskPriorityNumber(rpn);
        RiskAssessment savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    // Get RiskAssessment by ID
    public RiskAssessmentDto getRiskAssessmentById(int id) {
        Optional<RiskAssessment> optionalRiskAssessment = repository.findById(id);
        if (optionalRiskAssessment.isPresent()) {
            return mapper.toDto(optionalRiskAssessment.get());
        }
        throw new RuntimeException("RiskAssessment not found with ID: " + id); // Handle exception properly
    }

    // Get all RiskAssessments
    public List<RiskAssessmentDto> getAllRiskAssessments() {
        List<RiskAssessment> riskAssessments = repository.findAll();
        return mapper.toDtoList(riskAssessments);
    }

    // Update RiskAssessment
    public RiskAssessmentDto updateRiskAssessment(int id, RiskAssessmentDto riskAssessmentDto) {
        Optional<RiskAssessment> optionalRiskAssessment = repository.findById(id);
        if (optionalRiskAssessment.isPresent()) {
            RiskAssessment existingEntity = optionalRiskAssessment.get();

            // Update fields

            existingEntity.setAssessmentDate(riskAssessmentDto.getAssessmentDate());
            existingEntity.setRiskProbability(riskAssessmentDto.getRiskProbability());
            existingEntity.setRiskImpact(riskAssessmentDto.getRiskImpact());
            existingEntity.setRiskDetection(riskAssessmentDto.getRiskDetection());
            existingEntity.setRiskPriorityNumber(riskAssessmentDto.getRiskPriorityNumber());
            existingEntity.setMitigationAction(riskAssessmentDto.getMitigationAction());
            existingEntity.setRemarks(riskAssessmentDto.getRemarks());

            // Save and return updated DTO
            RiskAssessment updatedEntity = repository.save(existingEntity);
            return mapper.toDto(updatedEntity);
        }
        throw new RuntimeException("RiskAssessment not found with ID: " + id); // Handle exception properly
    }

    // Delete RiskAssessment
    public boolean deleteRiskAssessment(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true; // Deleted successfully
        }
        return false; // RiskAssessment not found
    }
}
