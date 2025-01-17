package com.building_mannager_system.controller.work_registration;

import com.building_mannager_system.dto.requestDto.work_registration.RepairRequestDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.service.work_registration.RepairRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repair-requests")
public class RepairRequestController {
    @Autowired
    private RepairRequestService repairRequestService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;  // ✅ Inject WebSocket Template

    private static final Long ADMIN_ACCOUNT_ID = 1L;  // ✅ Xác định ID admin cố định

    // ✅ Tạo yêu cầu sửa chữa và gửi WebSocket đến admin cụ thể
    @PostMapping("/send")
    public ResponseEntity<ApiResponce<RepairRequestDto>> sendRepairRequest(@RequestBody RepairRequestDto dto) {
        RepairRequestDto savedRequest = repairRequestService.createRepairRequest(dto);

        // ✅ Gửi thông báo qua WebSocket đến admin cụ thể có `accountId = 1`
        messagingTemplate.convertAndSend("/topic/admin/repair-requests/" + ADMIN_ACCOUNT_ID, savedRequest);
         ApiResponce<RepairRequestDto> apiResponce = new ApiResponce<>(200,savedRequest,"Success");
        return ResponseEntity.ok(apiResponce);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce<RepairRequestDto>> updateStatusRepairRequest(@PathVariable int id) {
        try {
            // Calling the service to update the repair request by ID
            RepairRequestDto repairRequestDto = repairRequestService.updateRepairRequest(id);

            // Creating a successful response
            ApiResponce<RepairRequestDto> apiResponse = new ApiResponce<>(200, repairRequestDto, "success");
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            // Handling errors and returning an appropriate response
            ApiResponce<RepairRequestDto> errorResponse = new ApiResponce<>(500, null, "Error updating repair request");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // ✅ Lấy tất cả yêu cầu cho Admin
    @GetMapping()
    public ResponseEntity<ApiResponce<List<RepairRequestDto>>> getAllRepairRequests() {

        ApiResponce<List<RepairRequestDto>> responce = new ApiResponce<>(200,repairRequestService.getAllRepairRequests(), "success");

        return ResponseEntity.ok(responce);
    }
}
