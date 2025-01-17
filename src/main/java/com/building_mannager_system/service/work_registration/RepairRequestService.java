package com.building_mannager_system.service.work_registration;

import com.building_mannager_system.dto.requestDto.work_registration.RepairRequestDto;
import com.building_mannager_system.entity.customer_service.work_registration.RepairRequest;
import com.building_mannager_system.enums.RequestStatus;
import com.building_mannager_system.mapper.customerMapper.RepairRequestMapper;
import com.building_mannager_system.repository.work_registration.RepairRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepairRequestService {

    @Autowired
    private RepairRequestRepository repairRequestRepository;

    @Autowired
    private RepairRequestMapper repairRequestMapper;

    // ✅ Tạo mới RepairRequest
    public RepairRequestDto createRepairRequest(RepairRequestDto dto) {
        RepairRequest entity = repairRequestMapper.toEntity(dto);
        entity.setStatus(RequestStatus.PENDING);
        RepairRequest savedEntity = repairRequestRepository.save(entity);
        return repairRequestMapper.toDto(savedEntity);
    }

    // ✅ Lấy tất cả RepairRequests
    public List<RepairRequestDto> getAllRepairRequests() {
        return repairRequestRepository.findAll()
                .stream()
                .map(repairRequestMapper::toDto)
                .collect(Collectors.toList());
    }

    // ✅ Lấy RepairRequest theo ID
    public RepairRequestDto getRepairRequestById(int id) {
        RepairRequest entity = repairRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repair Request not found"));
        return repairRequestMapper.toDto(entity);
    }

    // ✅ Cập nhật RepairRequest
    public RepairRequestDto updateRepairRequest(int id) {
        RepairRequest existingEntity = repairRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repair Request not found"));
             existingEntity.setStatus(RequestStatus.SUCCESS);
        repairRequestRepository.save(existingEntity);
        return repairRequestMapper.toDto(existingEntity);
    }

    // ✅ Xóa RepairRequest
    public void deleteRepairRequest(int id) {
        repairRequestRepository.deleteById(id);
    }
}
