package com.building_mannager_system.controller.notificationController;


import com.building_mannager_system.dto.requestDto.notificationDto.MaintenceTaskDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.entity.property_manager.MaintenanceTask;
import com.building_mannager_system.service.notification.MaintenanceTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/task")
public class MaintenanceTaskController {
    @Autowired
    private MaintenanceTaskService maintenanceTaskService;
    // Create Task
    @PostMapping
    public ResponseEntity<ApiResponce<MaintenceTaskDto>> createTask(@RequestBody MaintenceTaskDto task) {
        MaintenceTaskDto createdTask = maintenanceTaskService.createMaintenanceTask(task);
        ApiResponce<MaintenceTaskDto> response = new ApiResponce<>(200, createdTask, "success");
        return ResponseEntity.ok(response);
    }

    // Read All Tasks
    @GetMapping
    public ResponseEntity<ApiResponce<List<MaintenceTaskDto>>> getAllTasks() {
        List<MaintenceTaskDto> tasks = maintenanceTaskService.getAllMaintenanceTasks();
        ApiResponce<List<MaintenceTaskDto>> response = new ApiResponce<>(200,tasks, "Tasks retrieved successfully");
        return ResponseEntity.ok(response);
    }

    // Read Task By ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponce<MaintenceTaskDto>> getTaskById(@PathVariable int id) {
        MaintenceTaskDto task = maintenanceTaskService.getMaintenanceTaskById(id);
        if (task != null) {
            ApiResponce<MaintenceTaskDto> response = new ApiResponce<>(200,task,"Task retrieved successfully");
            return ResponseEntity.ok(response);
        } else {
            ApiResponce<MaintenceTaskDto> response = new ApiResponce<>(200, null,"Task not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

//    // Update Task
//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponce<MaintenceTaskDto>> updateTask(@PathVariable Long id, @RequestBody MaintenanceTask taskDetails) {
//        MaintenanceTask updatedTask = maintenanceTaskService.
//        if (updatedTask != null) {
//            ApiResponce<MaintenceTaskDto> response = new ApiResponce<>(200, updatedTask,"Task updated successfully");
//            return ResponseEntity.ok(response);
//        } else {
//            ApiResponce<MaintenceTaskDto> response = new ApiResponce<>(200, null,"Task not found");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        }
//    }
//
//    // Delete Task
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponce<Void>> deleteTask(@PathVariable Long id) {
//        boolean isDeleted = maintenanceTaskService.deleteTask(id);
//        if (isDeleted) {
//            ApiResponce<Void> response = new ApiResponce<>(200, null,"Task deleted successfully");
//            return ResponseEntity.ok(response);
//        } else {
//            ApiResponce<Void> response = new ApiResponce<>(200, null,"Task not found");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        }
//    }

}
