package com.building_mannager_system.service.system_service;

import com.building_mannager_system.dto.requestDto.propertyDto.QuotationDto;
import com.building_mannager_system.entity.property_manager.Quotation;
import com.building_mannager_system.mapper.propertiMapper.QuotationMapper;
import com.building_mannager_system.repository.system_manager.QuotationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuotationService {
    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private QuotationMapper quotationMapper;

    // Tạo mới báo giá
    public QuotationDto createQuotation(QuotationDto quotationDto) {
        // Chuyển từ DTO sang entity
        Quotation quotation = quotationMapper.toEntity(quotationDto);

        // Lưu vào cơ sở dữ liệu
        Quotation savedQuotation = quotationRepository.save(quotation);

        // Chuyển entity đã lưu sang DTO
        return quotationMapper.toDto(savedQuotation);
    }

    // Lấy tất cả báo giá
    public List<QuotationDto> getAllQuotations() {
        List<Quotation> quotations = quotationRepository.findAll();
        return quotationMapper.toDtoList(quotations);
    }

    // Lấy báo giá theo ID
    public QuotationDto getQuotationById(int id) {
        Optional<Quotation> quotation = quotationRepository.findById(id);
        if (quotation.isPresent()) {
            return quotationMapper.toDto(quotation.get());
        }
        throw new EntityNotFoundException("Quotation not found with id " + id);
    }

    // Cập nhật báo giá
    public QuotationDto updateQuotation(int id, QuotationDto quotationDto) {
        Optional<Quotation> existingQuotation = quotationRepository.findById(id);
        if (existingQuotation.isPresent()) {
            Quotation quotation = existingQuotation.get();
           // quotationMapper.updateEntityFromDto(quotationDto, quotation);
            Quotation updatedQuotation = quotationRepository.save(quotation);
            return quotationMapper.toDto(updatedQuotation);
        }
        throw new EntityNotFoundException("Quotation not found with id " + id);
    }

    // Xóa báo giá
    public void deleteQuotation(int id) {
        Optional<Quotation> quotation = quotationRepository.findById(id);
        if (quotation.isPresent()) {
            quotationRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Quotation not found with id " + id);
        }
    }
}
