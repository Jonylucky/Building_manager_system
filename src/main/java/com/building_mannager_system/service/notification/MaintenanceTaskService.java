package com.building_mannager_system.service.notification;

import com.building_mannager_system.dto.requestDto.notificationDto.MaintenceTaskDto;
import com.building_mannager_system.entity.property_manager.MaintenanceTask;
import com.building_mannager_system.enums.MaintenanceType;
import com.building_mannager_system.mapper.notification.MaintenanceTaskMapper;
import com.building_mannager_system.repository.notification.MaintenanceTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintenanceTaskService {

    @Autowired
    private MaintenanceTaskRepository maintenanceTaskRepository;
    @Autowired
    private MaintenanceTaskMapper maintenanceTaskMapper;
    // Tạo mới công việc bảo trì
    public MaintenceTaskDto createMaintenanceTask(MaintenceTaskDto maintenanceTaskDto) {
        MaintenanceTask maintenanceTask = maintenanceTaskMapper.toEntity(maintenanceTaskDto);
        MaintenanceTask savedTask = maintenanceTaskRepository.save(maintenanceTask);
        return maintenanceTaskMapper.toDTO(savedTask);
    }

    // Lấy tất cả công việc bảo trì
    public List<MaintenceTaskDto> getAllMaintenanceTasks() {
        List<MaintenanceTask> tasks = maintenanceTaskRepository.findAll();
        return tasks.stream()
                .map(maintenanceTaskMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Lấy công việc bảo trì theo ID
    public MaintenceTaskDto getMaintenanceTaskById(int id) {
        MaintenanceTask maintenanceTask = maintenanceTaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance Task not found with id " + id));
        return maintenanceTaskMapper.toDTO(maintenanceTask);
    }

}
