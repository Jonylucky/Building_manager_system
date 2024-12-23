package com.building_mannager_system.controller.propertyController;


import com.building_mannager_system.dto.requestDto.systemDto.SubcontractorDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.service.property_manager.SubcontractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subcontract")
public class SubcontractController {

    @Autowired
    private SubcontractorService subcontractService;
    // Create a new subcontract
    @PostMapping
    public ResponseEntity<ApiResponce<SubcontractorDto>> createSubcontract(@RequestBody  SubcontractorDto subcontractDto) {
        SubcontractorDto createdSubcontract = subcontractService.createSubcontractor(subcontractDto);
        ApiResponce<SubcontractorDto> response = new ApiResponce<>(200, createdSubcontract,"Subcontract created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all subcontracts
    @GetMapping
    public ResponseEntity<ApiResponce<List<SubcontractorDto>>> getAllSubcontracts() {
        List<SubcontractorDto> subcontracts = subcontractService.getAllSubcontractors();
        ApiResponce<List<SubcontractorDto>> response = new ApiResponce<>(200,subcontracts,"Subcontracts retrieved successfully");
        return ResponseEntity.ok(response);
    }

    // Get subcontract by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponce<SubcontractorDto>> getSubcontractById(@PathVariable int id) {
        SubcontractorDto subcontract = subcontractService.getSubcontractorById(id);
        ApiResponce<SubcontractorDto> response = new ApiResponce<>(200,subcontract,"Subcontract retrieved successfully");
        return ResponseEntity.ok(response);
    }

    // Update a subcontract
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce<SubcontractorDto>> updateSubcontract(
            @PathVariable int id,
            @RequestBody SubcontractorDto subcontractDto) {
        SubcontractorDto updatedSubcontract = subcontractService.updateSubcontractor(id, subcontractDto);
        ApiResponce<SubcontractorDto> response = new ApiResponce<>(200,updatedSubcontract,"Subcontract updated successfully");
        return ResponseEntity.ok(response);
    }

    // Delete a subcontract
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponce<Void>> deleteSubcontract(@PathVariable int id) {
        boolean isDeleted = subcontractService.deleteSubcontractor(id);
        if (isDeleted) {
            ApiResponce<Void> response = new ApiResponce<>(200,null,"Subcontract deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            ApiResponce<Void> response = new ApiResponce<>(200, null,"Subcontract not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
