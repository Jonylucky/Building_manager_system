package com.building_mannager_system.controller.propertyController;


import com.building_mannager_system.dto.requestDto.systemDto.SystemDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.entity.property_manager.Systems;
import com.building_mannager_system.service.property_manager.SystemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/systems")
public class SystemsController {
    @Autowired
    private SystemsService systemsService;
    // Create System
    @PostMapping
    public ResponseEntity<ApiResponce<SystemDto>> createSystem(@RequestBody SystemDto systemDto) {
        SystemDto createdSystem = systemsService.createSystem(systemDto);
        ApiResponce<SystemDto> response = new ApiResponce<>(200,  createdSystem,"System created successfully");
        return ResponseEntity.ok(response);
    }

    // Get All Systems
    @GetMapping
    public ResponseEntity<ApiResponce<List<SystemDto>>> getAllSystems() {
        List<SystemDto> systems = systemsService.getAllSystems();
        ApiResponce<List<SystemDto>> response = new ApiResponce<>(200,systems,"Systems retrieved successfully");
        return ResponseEntity.ok(response);
    }

    // Get System By ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponce<SystemDto>> getSystemById(@PathVariable Long id) {
        SystemDto system = systemsService.getSystemById(id);
        if (system != null) {
            ApiResponce<SystemDto> response = new ApiResponce<>(200,system,"System retrieved successfully");
            return ResponseEntity.ok(response);
        } else {
            ApiResponce<SystemDto> response = new ApiResponce<>(500, null,"System not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Update System
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce<SystemDto>> updateSystem(@PathVariable Long id, @RequestBody SystemDto systemDetails) {
        SystemDto updatedSystem = systemsService.updateSystem(id, systemDetails);
        if (updatedSystem != null) {
            ApiResponce<SystemDto> response = new ApiResponce<>(200,updatedSystem,"System updated successfully");
            return ResponseEntity.ok(response);
        } else {
            ApiResponce<SystemDto> response = new ApiResponce<>(500,null,"System not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete System
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponce<Void>> deleteSystem(@PathVariable Long id) {
        boolean isDeleted = systemsService.deleteSystem(id);
        if (isDeleted) {
            ApiResponce<Void> response = new ApiResponce<>(200,null,"System deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            ApiResponce<Void> response = new ApiResponce<>(500,null,"System not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
