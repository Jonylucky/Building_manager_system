package com.building_mannager_system.controller.propertyController;

import com.building_mannager_system.dto.requestDto.propertyDto.RepairProposalDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.service.system_service.RepairProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repair-proposals")
public class RepairProposalController {


    @Autowired
    private RepairProposalService repairProposalService;

    // Tạo mới đề xuất sửa chữa/thay thế
    @PostMapping
    public ResponseEntity<ApiResponce<RepairProposalDto>> createRepairProposal(@RequestBody RepairProposalDto repairProposalDto) {
        RepairProposalDto createdProposal = repairProposalService.createRepairProposal(repairProposalDto);
        ApiResponce<RepairProposalDto> response = new ApiResponce<>(200,createdProposal,"Repair proposal created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Lấy tất cả các đề xuất sửa chữa/thay thế
    @GetMapping
    public ResponseEntity<ApiResponce<List<RepairProposalDto>>> getAllRepairProposals() {
        List<RepairProposalDto> proposals = repairProposalService.getAllRepairProposals();
        ApiResponce<List<RepairProposalDto>> response = new ApiResponce<>(200,proposals, "Fetched all repair proposals");
        return ResponseEntity.ok(response);
    }

    // Lấy đề xuất sửa chữa/thay thế theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponce<RepairProposalDto>> getRepairProposalById(@PathVariable int id) {
        RepairProposalDto repairProposalDto = repairProposalService.getRepairProposalById(id);
        ApiResponce<RepairProposalDto> response = new ApiResponce<>(200,repairProposalDto, "Repair proposal fetched successfully");
        return ResponseEntity.ok(response);
    }

    // Cập nhật đề xuất sửa chữa/thay thế
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce<RepairProposalDto>> updateRepairProposal(@PathVariable int id, @RequestBody RepairProposalDto repairProposalDto) {
        RepairProposalDto updatedProposal = repairProposalService.updateRepairProposal(id, repairProposalDto);
        ApiResponce<RepairProposalDto> response = new ApiResponce<>(200,updatedProposal, "Repair proposal updated successfully");
        return ResponseEntity.ok(response);
    }

    // Xóa đề xuất sửa chữa/thay thế
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponce<Void>> deleteRepairProposal(@PathVariable int id) {
        repairProposalService.deleteRepairProposal(id);
        ApiResponce<Void> response = new ApiResponce<>(200,null, "Repair proposal deleted successfully");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
