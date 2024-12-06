package com.building_mannager_system.controller.propertyController;

import com.building_mannager_system.dto.requestDto.propertyDto.HandoverStatusDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.service.property_manager.HandoverStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/handover-status")

public class HanhoverStatusController {
    @Autowired
    private HandoverStatusService handoverStatusService;
    // Create or update a handover status
    @PostMapping
    public ApiResponce<HandoverStatusDto> saveOrUpdateHandover(@RequestBody HandoverStatusDto handoverDto) {
        try {
            HandoverStatusDto savedHandover = handoverStatusService.saveOrUpdateHandover(handoverDto);
            return new ApiResponce<>(200, savedHandover, "Success");
        } catch (Exception e) {
            return new ApiResponce<>(500, null, "Failed: " + e.getMessage());
        }
    }

    // Get all handover statuses
    @GetMapping
    public ApiResponce<List<HandoverStatusDto>> getAllHandovers() {
        try {
            List<HandoverStatusDto> handovers = handoverStatusService.getAllHandovers();
            return new ApiResponce<>(200, handovers, "Success");
        } catch (Exception e) {
            return new ApiResponce<>(500, null, "Failed: " + e.getMessage());
        }
    }

    // Get a specific handover status by ID
    @GetMapping("/{id}")
    public ApiResponce<HandoverStatusDto> getHandoverById(@PathVariable Integer id) {
        try {
            HandoverStatusDto handover = handoverStatusService.getHandoverById(id);
            return new ApiResponce<>(200, handover, "Success");
        } catch (Exception e) {
            return new ApiResponce<>(404, null, "Handover not found: " + e.getMessage());
        }
    }

    // Delete a specific handover status by ID
    @DeleteMapping("/{id}")
    public ApiResponce<Void> deleteHandover(@PathVariable Integer id) {
        try {
            handoverStatusService.deleteHandover(id);
            return new ApiResponce<>(200, null, "Deleted successfully");
        } catch (Exception e) {
            return new ApiResponce<>(500, null, "Failed: " + e.getMessage());
        }
    }
}
