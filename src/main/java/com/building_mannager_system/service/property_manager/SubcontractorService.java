package com.building_mannager_system.service.property_manager;

import com.building_mannager_system.dto.requestDto.systemDto.SubcontractorDto;
import com.building_mannager_system.entity.property_manager.Subcontractor;
import com.building_mannager_system.mapper.propertiMapper.SubcontractorsMapper;
import com.building_mannager_system.repository.system_manager.SubcontractorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubcontractorService {

    @Autowired
    private SubcontractorsRepository subcontractorsRepository;
    @Autowired
    private SubcontractorsMapper subcontractorsMapper;

    public SubcontractorDto createSubcontractor(SubcontractorDto subcontractorDto) {
        Subcontractor subcontractor = subcontractorsMapper.toEntity(subcontractorDto);
        Subcontractor savedSubcontractor = subcontractorsRepository.save(subcontractor);
        return subcontractorsMapper.toDto(savedSubcontractor);
    }

    public List<SubcontractorDto> getAllSubcontractors() {
        List<Subcontractor> subcontractors = subcontractorsRepository.findAll();
        return subcontractors.stream()
                .map(subcontractorsMapper::toDto)
                .collect(Collectors.toList());
    }

    public SubcontractorDto getSubcontractorById(int id) {
        Optional<Subcontractor> optionalSubcontractor = subcontractorsRepository.findById(id);
        return optionalSubcontractor.map(subcontractorsMapper::toDto).orElse(null);
    }

    public SubcontractorDto updateSubcontractor( int id,SubcontractorDto subcontractorDto) {
        // Tìm subcontractor theo ID
        Subcontractor existingSubcontractor = subcontractorsRepository.findById(id)
                .orElse(null);
        if (existingSubcontractor == null) return null;

// Cập nhật các trường từ DTO vào thực thể
        existingSubcontractor.setName(subcontractorDto.getName());
        existingSubcontractor.setRating(subcontractorDto.getRating());
        existingSubcontractor.setPhone(subcontractorDto.getPhone());
        // Thêm các trường khác nếu có trong SubcontractorDto

        // Lưu thực thể đã cập nhật
        Subcontractor updatedSubcontractor = subcontractorsRepository.save(existingSubcontractor);

        // Trả về DTO
        return subcontractorsMapper.toDto(updatedSubcontractor);
        // Cập nhật các trường từ DTO vào thực thể

    }

    public boolean deleteSubcontractor(int id) {
        if (subcontractorsRepository.existsById(id)) {
            subcontractorsRepository.deleteById(id);
            return true; // Xóa thành công
        }
        return false; // Không tìm thấy Subcontractor
    }




}
