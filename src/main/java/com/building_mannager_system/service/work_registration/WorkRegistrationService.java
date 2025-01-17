package com.building_mannager_system.service.work_registration;


import com.building_mannager_system.dto.requestDto.work_registration.WorkRegistrationDto;
import com.building_mannager_system.entity.customer_service.work_registration.WorkRegistration;
import com.building_mannager_system.enums.WorkStatus;
import com.building_mannager_system.mapper.customerMapper.WorkRegistrationMapper;
import com.building_mannager_system.repository.work_registration.WorkRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkRegistrationService {
    @Autowired
    private WorkRegistrationMapper workRegistrationMapper;

    @Autowired
    private WorkRegistrationRepository workRegistrationRepository;

    public WorkRegistrationDto createWorkRegistration(WorkRegistrationDto dto) {
        WorkRegistration entity = workRegistrationMapper.toEntity(dto);
        entity.setStatus(WorkStatus.PENDING);
        WorkRegistration savedEntity = workRegistrationRepository.save(entity);
        return workRegistrationMapper.toDto(savedEntity);
    }
    // ✅ Lấy tất cả
    public List<WorkRegistrationDto> getAllWorkRegistrations() {
        return workRegistrationRepository.findAll()
                .stream()
                .map(workRegistrationMapper::toDto)
                .collect(Collectors.toList());
    }

    // ✅ Lấy theo ID
    public WorkRegistrationDto getWorkRegistrationById(int id) {
        WorkRegistration entity = workRegistrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WorkRegistration not found"));
        return workRegistrationMapper.toDto(entity);
    }

    // ✅ Cập nhật trạng thái công việc
    public WorkRegistrationDto updateWorkRegistration(int id, String status) {
        // Tìm kiếm bản ghi dựa trên ID
        WorkRegistration existingEntity = workRegistrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WorkRegistration not found"));

        try {
            // Chuyển đổi chuỗi thành enum và cập nhật trạng thái
            WorkStatus updatedStatus = WorkStatus.valueOf(status.toUpperCase());
            existingEntity.setStatus(updatedStatus);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid work status provided");
        }

        // Lưu lại thay đổi
        WorkRegistration savedEntity = workRegistrationRepository.save(existingEntity);

        // Chuyển đổi và trả về DTO
        return workRegistrationMapper.toDto(savedEntity);
    }

    // ✅ Xóa
    public void deleteWorkRegistration(int id) {
        workRegistrationRepository.deleteById(id);
    }
}
