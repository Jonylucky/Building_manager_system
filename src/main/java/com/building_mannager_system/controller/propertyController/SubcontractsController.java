package com.building_mannager_system.controller.propertyController;

import com.building_mannager_system.dto.requestDto.systemDto.SubcontractorDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.service.property_manager.SubcontractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcontractors")
public class SubcontractsController {
    @Autowired
    private SubcontractorService subcontractorService;

    @PostMapping
    public ResponseEntity<ApiResponce<SubcontractorDto>> createSubcontractor(@RequestBody  SubcontractorDto subcontractorDto) {
        SubcontractorDto createdSubcontractor = subcontractorService.createSubcontractor(subcontractorDto);
        return ResponseEntity.ok(new ApiResponce<>(201, createdSubcontractor, "Subcontractor created successfully"));
    }


    @GetMapping
    public ResponseEntity<ApiResponce<List<SubcontractorDto>>> getAllSubcontractors() {
        List<SubcontractorDto> subcontractors = subcontractorService.getAllSubcontractors();
        return ResponseEntity.ok(new ApiResponce<>(200, subcontractors, "Subcontractors fetched successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponce<SubcontractorDto>> getSubcontractorById(@PathVariable int id) {
        SubcontractorDto subcontractor = subcontractorService.getSubcontractorById(id);
        return ResponseEntity.ok(new ApiResponce<>(200, subcontractor, "Subcontractor fetched successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce<SubcontractorDto>> updateSubcontractor(@PathVariable int id, @RequestBody SubcontractorDto subcontractorDto) {

        SubcontractorDto updatedSubcontractor = subcontractorService.updateSubcontractor( id,subcontractorDto);
        return ResponseEntity.ok(new ApiResponce<>(200, updatedSubcontractor, "Subcontractor updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponce<String>> deleteSubcontractor(@PathVariable int id) {
        subcontractorService.deleteSubcontractor(id);
        return ResponseEntity.ok(new ApiResponce<>(204, "Subcontractor deleted successfully", "Success"));
    }
}
