package com.building_mannager_system.controller.propertyController;

import com.building_mannager_system.dto.requestDto.propertyDto.QuotationDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.service.system_service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Repository
@RequestMapping("/api/quotations")
public class QuotationServiceController {

    @Autowired
    private QuotationService quotationService;

    // Tạo mới báo giá
    @PostMapping
    public ResponseEntity<ApiResponce<QuotationDto>> createQuotation(@RequestBody QuotationDto quotationDto) {
        QuotationDto createdQuotation = quotationService.createQuotation(quotationDto);
        ApiResponce<QuotationDto> response = new ApiResponce<>(200,createdQuotation,"Quotation created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Lấy tất cả báo giá
    @GetMapping
    public ResponseEntity<ApiResponce<List<QuotationDto>>> getAllQuotations() {
        List<QuotationDto> quotations = quotationService.getAllQuotations();
        ApiResponce<List<QuotationDto>> response = new ApiResponce<>(200,quotations,"Fetched all quotations");
        return ResponseEntity.ok(response);
    }

    // Lấy báo giá theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponce<QuotationDto>> getQuotationById(@PathVariable int id) {
        QuotationDto quotationDto = quotationService.getQuotationById(id);
        ApiResponce<QuotationDto> response = new ApiResponce<>(200,quotationDto, "Quotation fetched successfully");
        return ResponseEntity.ok(response);
    }

    // Cập nhật báo giá
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce<QuotationDto>> updateQuotation(@PathVariable int id, @RequestBody QuotationDto quotationDto) {
        QuotationDto updatedQuotation = quotationService.updateQuotation(id, quotationDto);
        ApiResponce<QuotationDto> response = new ApiResponce<>(200,updatedQuotation, "Quotation updated successfully");
        return ResponseEntity.ok(response);
    }

    // Xóa báo giá
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponce<Void>> deleteQuotation(@PathVariable int id) {
        quotationService.deleteQuotation(id);
        ApiResponce<Void> response = new ApiResponce<>(200, null,"Quotation deleted successfully" );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
