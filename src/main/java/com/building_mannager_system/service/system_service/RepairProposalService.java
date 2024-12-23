package com.building_mannager_system.service.system_service;

import com.building_mannager_system.dto.requestDto.propertyDto.RepairProposalDto;
import com.building_mannager_system.entity.property_manager.RepairProposal;
import com.building_mannager_system.mapper.propertiMapper.RepairProposalMapper;
import com.building_mannager_system.repository.system_manager.RepairProposalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairProposalService {
    @Autowired
    private RepairProposalRepository repairProposalRepository;

    @Autowired
    private RepairProposalMapper repairProposalMapper;

    public RepairProposalDto createRepairProposal(RepairProposalDto repairProposalDto) {
        // Chuyển từ DTO sang entity
        RepairProposal repairProposal = repairProposalMapper.toEntity(repairProposalDto);

        // Lưu entity vào cơ sở dữ liệu
        RepairProposal savedRepairProposal = repairProposalRepository.save(repairProposal);

        // Chuyển từ entity sang DTO để trả về client
        return repairProposalMapper.toDto(savedRepairProposal);
    }
    // 2. Lấy danh sách tất cả đề xuất sửa chữa
    public List<RepairProposalDto> getAllRepairProposals() {
        List<RepairProposal> repairProposals = repairProposalRepository.findAll();
        return repairProposalMapper.toDtoList(repairProposals);
    }

    // 3. Lấy đề xuất sửa chữa theo ID
    public RepairProposalDto getRepairProposalById(int id) {
        Optional<RepairProposal> repairProposal = repairProposalRepository.findById(id);
        if (repairProposal.isPresent()) {
            return repairProposalMapper.toDto(repairProposal.get());
        }
        throw new EntityNotFoundException("Repair proposal not found with id " + id);
    }

    // 4. Cập nhật đề xuất sửa chữa
    public RepairProposalDto updateRepairProposal(int id, RepairProposalDto repairProposalDto) {
        Optional<RepairProposal> existingProposal = repairProposalRepository.findById(id);
        if (existingProposal.isPresent()) {
            // Cập nhật entity với dữ liệu mới
            RepairProposal repairProposal = existingProposal.get();

           // repairProposalMapper.updateEntityFromDto(repairProposalDto, repairProposal);
            RepairProposal updatedProposal = repairProposalRepository.save(repairProposal);
            return repairProposalMapper.toDto(updatedProposal);
        }
        throw new EntityNotFoundException("Repair proposal not found with id " + id);
    }

    // 5. Xóa đề xuất sửa chữa
    public void deleteRepairProposal(int id) {
        Optional<RepairProposal> repairProposal = repairProposalRepository.findById(id);
        if (repairProposal.isPresent()) {
            repairProposalRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Repair proposal not found with id " + id);
        }
    }
}