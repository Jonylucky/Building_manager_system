package com.building_mannager_system.controller.propertyController;


import com.building_mannager_system.dto.requestDto.propertyDto.RiskAssessmentDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.service.system_service.RiskAssessmentServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/riskAssessment")
public class RiskAssessmentController {
    @Autowired
    private RiskAssessmentServcie riskAssessmentServcie;
    // Create RiskAssessment
    @PostMapping
    public ResponseEntity<ApiResponce<RiskAssessmentDto>> createRiskAssessment(@RequestBody RiskAssessmentDto riskAssessmentDto) {
        RiskAssessmentDto createdDto = riskAssessmentServcie.createRiskAssessment(riskAssessmentDto);
        ApiResponce<RiskAssessmentDto> response = new ApiResponce<>(HttpStatus.CREATED.value(), createdDto, "Risk assessment created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get RiskAssessment by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponce<RiskAssessmentDto>> getRiskAssessmentById(@PathVariable int id) {
        try {
            RiskAssessmentDto riskAssessmentDto = riskAssessmentServcie.getRiskAssessmentById(id);
            ApiResponce<RiskAssessmentDto> response = new ApiResponce<>(HttpStatus.OK.value(), riskAssessmentDto, "Risk assessment retrieved successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            ApiResponce<RiskAssessmentDto> response = new ApiResponce<>(HttpStatus.NOT_FOUND.value(), null, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Get all RiskAssessments
    @GetMapping
    public ResponseEntity<ApiResponce<List<RiskAssessmentDto>>> getAllRiskAssessments() {
        List<RiskAssessmentDto> riskAssessments = riskAssessmentServcie.getAllRiskAssessments();
        ApiResponce<List<RiskAssessmentDto>> response = new ApiResponce<>(HttpStatus.OK.value(), riskAssessments, "All risk assessments retrieved successfully");
        return ResponseEntity.ok(response);
    }

    // Update RiskAssessment
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce<RiskAssessmentDto>> updateRiskAssessment(@PathVariable int id, @RequestBody RiskAssessmentDto riskAssessmentDto) {
        try {
            RiskAssessmentDto updatedDto = riskAssessmentServcie.updateRiskAssessment(id, riskAssessmentDto);
            ApiResponce<RiskAssessmentDto> response = new ApiResponce<>(HttpStatus.OK.value(), updatedDto, "Risk assessment updated successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            ApiResponce<RiskAssessmentDto> response = new ApiResponce<>(HttpStatus.NOT_FOUND.value(), null, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete RiskAssessment
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponce<Void>> deleteRiskAssessment(@PathVariable int id) {
        boolean isDeleted = riskAssessmentServcie.deleteRiskAssessment(id);
        if (isDeleted) {
            ApiResponce<Void> response = new ApiResponce<>(HttpStatus.OK.value(), null, "Risk assessment deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            ApiResponce<Void> response = new ApiResponce<>(HttpStatus.NOT_FOUND.value(), null, "Risk assessment not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
