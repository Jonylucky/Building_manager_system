package com.building_mannager_system.controller.work_registration;


import com.building_mannager_system.dto.requestDto.work_registration.WorkRegistrationDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.entity.customer_service.work_registration.WorkRegistration;
import com.building_mannager_system.service.work_registration.WorkRegistrationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.JstlUtils;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/work-registrations")
public class WorkRegistrationController {

    @Autowired
    private WorkRegistrationService workRegistrationService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private static final Long ADMIN_ACCOUNT_ID = 1L;  // ✅ Xác định ID admin cố định

    @GetMapping
    public ResponseEntity<ApiResponce<List<WorkRegistrationDto>>> getAllWorkRegistrations() {
        try {
            List<WorkRegistrationDto> workRegistrations = workRegistrationService.getAllWorkRegistrations();

            ApiResponce<List<WorkRegistrationDto>> apiResponce = new ApiResponce<>(200, workRegistrations, "success");

            return ResponseEntity.ok(apiResponce);
        } catch (Exception e) {
            ApiResponce<List<WorkRegistrationDto>> errorResponse = new ApiResponce<>(500, null, "Error fetching work registrations");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/send")
    public ResponseEntity<ApiResponce<WorkRegistrationDto>> addWorkRegistration(@RequestBody WorkRegistrationDto workRegistrationDto) {
        try {
            WorkRegistrationDto response = workRegistrationService.createWorkRegistration(workRegistrationDto);

            messagingTemplate.convertAndSend("/topic/admin/work-registrations/" + ADMIN_ACCOUNT_ID, response);
            return ResponseEntity.ok(new ApiResponce<>(200, response, "success"));
        } catch (Exception e) {
            ApiResponce<WorkRegistrationDto> errorResponse = new ApiResponce<>(500, null, "Error adding work registration");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponce<WorkRegistrationDto>> getWorkRegistrationById(@PathVariable int id) {
        try {
            WorkRegistrationDto workRegistration = workRegistrationService.getWorkRegistrationById(id);
            ApiResponce<WorkRegistrationDto> apiResponce = new ApiResponce<>(200, workRegistration, "Success");
            return ResponseEntity.ok(apiResponce);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponce<>(404, null, "Work registration not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponce<>(500, null, "Error retrieving work registration"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce<WorkRegistrationDto>> updateWorkRegistration(
            @PathVariable int id,
            @RequestBody Map<String, String> statusMap) {
        try {
            String status = statusMap.get("status");
            // Attempt to update the work registration
            WorkRegistrationDto response = workRegistrationService.updateWorkRegistration(id, status);
            System.out.println(status);
            return ResponseEntity.ok(new ApiResponce<>(200, response, "Work registration updated successfully"));
        } catch (EntityNotFoundException e) {
            // Specific error handling for entity not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponce<>(404, null, "Work registration not found"));
        } catch (Exception e) {
            // General error handling
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponce<>(500, null, "Error updating work registration"));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponce<String>> deleteWorkRegistration(@PathVariable int id) {
        try {
            // Attempt to delete the work registration
            workRegistrationService.deleteWorkRegistration(id);
            return ResponseEntity.ok(new ApiResponce<>(200, "Work registration deleted successfully", "success"));
        } catch (EntityNotFoundException e) {
            // Specific error handling for entity not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponce<>(404, null, "Work registration not found"));
        } catch (Exception e) {
            // General error handling
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponce<>(500, null, "Error deleting work registration"));
        }
    }



}
