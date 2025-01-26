package com.building_mannager_system.service.verification_service;


import com.building_mannager_system.dto.requestDto.verificationDto.ElectricityUsageVerificationDto;
import com.building_mannager_system.entity.verification.ElectricityUsageVerification;
import com.building_mannager_system.enums.Status;
import com.building_mannager_system.mapper.verificationMapper.ElectricityUsageVerificationMapper;
import com.building_mannager_system.repository.verificationRepository.ElectricityUsageVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ElectricityUsageVerificationService {

    @Autowired
    private ElectricityUsageVerificationRepository electricityUsageVerificationRepository;

    @Autowired
    private ElectricityUsageVerificationMapper electricityUsageMapper;

    // Create

    public ElectricityUsageVerificationDto createElectricityUsageVerification(ElectricityUsageVerificationDto dto) {
        // Convert DTO to Entity
        ElectricityUsageVerification entity = electricityUsageMapper.toEntity(dto);

        // Save the entity to the database
        ElectricityUsageVerification savedEntity = electricityUsageVerificationRepository.save(entity);

        // Convert the saved entity back to DTO and return it
        return electricityUsageMapper.toDTO(savedEntity);
    }

    // Read (Find by ID)
    public ElectricityUsageVerificationDto getById(Integer id) {
        // Find entity by id
        Optional<ElectricityUsageVerification> entityOptional = electricityUsageVerificationRepository.findById(id);

        // If entity is present, map it to DTO, else throw exception
        return entityOptional
                .map(e -> electricityUsageMapper.toDTO(e))
                .orElse(null);
    }

    // Read (Find all)
    public List<ElectricityUsageVerificationDto> getAll() {
        List<ElectricityUsageVerification> entities = electricityUsageVerificationRepository.findAll();
        return electricityUsageMapper.toDTOList(entities);
    }
    //find by Status
    public  List<ElectricityUsageVerificationDto> getByStatus(Status status) {
        return null;
    }

    //find by MeterID
    public Page<ElectricityUsageVerificationDto> getVerificationsByMeterId(int meterId, int page, int size) {
        // Kiểm tra giá trị của page để tránh lỗi chỉ số âm
        int pageIndex = (page > 0) ? page - 1 : 0;

        // Tạo đối tượng phân trang với sắp xếp theo "readingDate" giảm dần
        Pageable pageable = PageRequest.of(pageIndex, size, Sort.by(Sort.Direction.DESC, "readingDate"));

        // Truy xuất dữ liệu từ repository
        Page<ElectricityUsageVerification> verificationPage = electricityUsageVerificationRepository.findByMeterId(meterId, pageable);

        // Chuyển đổi entity sang DTO bằng mapper và trả về
        return verificationPage.map(electricityUsageMapper::toDTO);
    }


    // Update
    @Transactional
    public ElectricityUsageVerificationDto update(Integer id, ElectricityUsageVerificationDto dto) {
        // Find the existing entity
        Optional<ElectricityUsageVerification> entityOptional = electricityUsageVerificationRepository.findById(id);

        // If entity is found, update it with the new values
        return entityOptional
                .map(existingEntity -> {
                    // Update the existing entity with values from the DTO
                    existingEntity.setStartReading(dto.getStartReading());
                    existingEntity.setEndReading(dto.getEndReading());
                    existingEntity.setReadingDate(dto.getReadingDate());
                    existingEntity.setImageName(dto.getImageName());
                    existingEntity.setPreviousMonthImageName(dto.getPreviousMonthImageName());
                    existingEntity.setStatus(Status.valueOf(dto.getStatus())); // Assuming Status is an enum
                    existingEntity.setPreviousMonthCost(dto.getPreviousMonthCost());
                    existingEntity.setCurrentMonthCost(dto.getCurrentMonthCost());

                    // Save and return the updated entity as DTO
                    ElectricityUsageVerification updatedEntity = electricityUsageVerificationRepository.save(existingEntity);
                    return electricityUsageMapper.toDTO(updatedEntity);
                })
                .orElse(null);
    }

    @Transactional
    public void delete(Integer id) {
        // Find entity by id and return null if not found
        ElectricityUsageVerification entity = electricityUsageVerificationRepository.findById(id).orElse(null);

        // If entity is found, delete it, otherwise do nothing
        if (entity != null) {
            electricityUsageVerificationRepository.delete(entity);
        } else {
            // Optionally, handle the case when the entity is not found
            // You can log it or simply return without throwing an exception
            System.out.println("ElectricityUsageVerification not found with id " + id);
        }
    }
}

