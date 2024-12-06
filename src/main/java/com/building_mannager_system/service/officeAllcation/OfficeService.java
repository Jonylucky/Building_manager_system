package com.building_mannager_system.service.officeAllcation;

import com.building_mannager_system.dto.requestDto.oficeSapceAllcationDto.OfficesDto;
import com.building_mannager_system.dto.requestDto.propertyDto.HandoverStatusDto;
import com.building_mannager_system.entity.customer_service.contact_manager.HandoverStatus;
import com.building_mannager_system.entity.customer_service.contact_manager.Office;
import com.building_mannager_system.mapper.officeMapper.OfficeMapper;
import com.building_mannager_system.repository.office.OfficceRepository;
import com.building_mannager_system.service.property_manager.HandoverStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeService {
    @Autowired
    private OfficceRepository officceRepository;
    @Autowired
    private OfficeMapper officeMapper;
    @Autowired
    private HandoverStatusService handoverStatusService;

    public Office findOfficeById(Integer id) {
        // Retrieve the office from the repository
        return officceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Office not found with ID: " + id));
    }
    public Office createOffice(OfficesDto officeCreateDto) {
        // Retrieve the HandoverStatus using the locationId
        HandoverStatusDto handover = handoverStatusService.getHandoverById(officeCreateDto.getLocationId());

        // Check if handover is null or not found
        if (handover == null) {
            throw new RuntimeException("HandoverStatus not found for Location ID: ");
        }



        Office office = officeMapper.toEntity(officeCreateDto);
        return officceRepository.save(office);
    }



}
