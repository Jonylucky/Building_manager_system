package com.building_mannager_system.service.system_service;

import com.building_mannager_system.dto.requestDto.customer.ContactDto;
import com.building_mannager_system.dto.requestDto.propertyDto.MeterDto;
import com.building_mannager_system.dto.someDto.MeterByContactDto;
import com.building_mannager_system.entity.customer_service.contact_manager.Office;
import com.building_mannager_system.entity.customer_service.customer_manager.Contact;
import com.building_mannager_system.entity.customer_service.system_manger.Meter;
import com.building_mannager_system.mapper.customerMapper.ContactMapper;
import com.building_mannager_system.mapper.propertiMapper.MeterMapper;
import com.building_mannager_system.repository.system_manager.MeterRepository;
import com.building_mannager_system.service.customer_service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeterService {
    @Autowired
    private MeterRepository meterRepository;
    @Autowired
    private MeterMapper meterMapper;  // Inject MeterMapper
    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    @Lazy
    private SomeFilterByMeterIdService someFilterByMeterIdService;
    @Autowired
    private ContactService contactService;
    // Create
    public MeterDto createMeter(MeterDto meterDTO) {
        // Chuyển MeterDTO thành Meter entity
        Meter meter = meterMapper.toEntity(meterDTO);
        // Lưu Meter vào cơ sở dữ liệu
        Meter createdMeter = meterRepository.save(meter);
        // Chuyển đổi lại thành MeterDTO
        return meterMapper.toDTO(createdMeter);
    }

    // Update
    public MeterDto updateMeter(Integer id, MeterDto meterDTO) {
        // Kiểm tra nếu Meter với id tồn tại
        if (meterRepository.existsById(id)) {
            // Chuyển MeterDTO thành Meter entity
            Meter meter = meterMapper.toEntity(meterDTO);
            meter.setId(id);  // Đảm bảo ID đúng khi cập nhật
            // Cập nhật Meter trong cơ sở dữ liệu
            Meter updatedMeter = meterRepository.save(meter);
            // Chuyển đổi lại thành MeterDTO
            return meterMapper.toDTO(updatedMeter);
        } else {
            throw new RuntimeException("Meter not found with ID: " + id);
        }
    }

    // Get All
    public List<MeterDto> getAllMeters() {
        List<Meter> meters = meterRepository.findAll();
        // Chuyển đổi danh sách Meter thành MeterDTO
        return meters.stream()
                .map(meterMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get By ID
    public MeterByContactDto getMeterById(Integer id) {
        Meter meter = meterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meter not found with ID: " + id));
        MeterDto meterDto = meterMapper.toDTO(meter);
        // Fetch ContactId using the refactored method
        Integer contactId = someFilterByMeterIdService.getContactIdFromMeterId(id);
        Contact contact = contactService.getContactById(contactId);
        ContactDto contactDto = contactMapper.toDTO(contact);
        MeterByContactDto meterByContactDto = new MeterByContactDto();

        meterByContactDto.setContact(contactDto);
        meterByContactDto.setMeter(meterDto);

        return meterByContactDto;

    }
    // Phương thức để lấy Office từ MeterId
    public Office getOfficeByMeterId(Integer meterId) {
        // Tìm Meter theo MeterId
        Meter meter = meterRepository.findById(meterId)
                .orElseThrow(() -> new RuntimeException("Meter not found for ID: " + meterId));

        // Lấy Office từ Meter (Giả sử mỗi Meter chỉ có một Office liên kết)
        Office office = meter.getOffice();  // Giả sử Meter có mối quan hệ ManyToOne với Office

        if (office == null) {
            throw new RuntimeException("Office not found for Meter with ID: " + meterId);
        }

        return office;
    }
    // Delete
    public void deleteMeter(Integer id) {
        if (meterRepository.existsById(id)) {
            meterRepository.deleteById(id);
        } else {
            throw new RuntimeException("Meter not found with ID: " + id);
        }
    }

}
