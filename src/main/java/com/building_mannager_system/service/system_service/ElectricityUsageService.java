package com.building_mannager_system.service.system_service;

import com.building_mannager_system.dto.requestDto.propertyDto.ElectricityUsageDTO;
import com.building_mannager_system.dto.requestDto.propertyDto.MeterDto;
import com.building_mannager_system.dto.requestDto.verificationDto.ElectricityUsageVerificationDto;
import com.building_mannager_system.entity.customer_service.system_manger.ElectricityUsage;
import com.building_mannager_system.entity.customer_service.system_manger.Meter;
import com.building_mannager_system.mapper.propertiMapper.ElectricityUsageMapper;
import com.building_mannager_system.repository.system_manager.ElectricityUsageRepository;
import com.building_mannager_system.service.notification.NotificationPaymentContractService;
import com.building_mannager_system.service.verification_service.ElectricityUsageVerificationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ElectricityUsageService {


    private final ElectricityUsageRepository electricityUsageRepository;

    @Autowired
    public ElectricityUsageService(ElectricityUsageRepository electricityUsageRepository) {
        this.electricityUsageRepository = electricityUsageRepository;
    }

    @Autowired
    private ElectricityUsageMapper electricityUsageMapper;
    @Autowired
    private MeterService meterService;
    @Autowired
    private ElectricityCostService electricityCostService;
    @Autowired
    private ElectricityUsageVerificationService electricityUsageVerificationService;
    @Autowired
    private SomeFilterByMeterIdService someFilterByMeterIdService;
    @Autowired
    private NotificationPaymentContractService notificationPaymentContractService;

    public List<ElectricityUsageDTO> getElectricityUsageByMeterAndDate(ElectricityUsageDTO dto) {
        // Lấy thông tin Meter theo meterId từ service Meter

        // Lấy ngày ghi từ DTO (sử dụng ngày hiện tại của tháng)
        //LocalDate currentDate = LocalDate.now();
        // Lấy ngày cụ thể: 20/07/2025
        LocalDate currentDate = LocalDate.of(2025, 1, 20);
        // Tính toán ngày 1 của tháng trước và ngày cuối của tháng trước
        LocalDate firstDayLastMonth = currentDate.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayLastMonth = currentDate.minusMonths(1).withDayOfMonth(currentDate.minusMonths(1).lengthOfMonth());

        // Truy vấn repository để lấy các bản ghi ElectricityUsage trong khoảng thời gian từ ngày 1 đến cuối tháng trước
        List<ElectricityUsage> usages = electricityUsageRepository.findByMeterIdAndReadingDateBetween(
                dto.getMeterId(),
                firstDayLastMonth,
                lastDayLastMonth
        );

        // Chuyển đổi danh sách Entity sang DTO
        return usages.stream().map(electricityUsageMapper::toDTO).collect(Collectors.toList());
    }

    public ElectricityUsageDTO createElectricityUsage(ElectricityUsageDTO dto) {
        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        LocalDate lastMonth20th = currentDate.minusMonths(1).withDayOfMonth(20);

        // Gọi phương thức để lấy danh sách sử dụng điện theo MeterId và khoảng thời gian
        List<ElectricityUsageDTO> lastMonthUsage = getElectricityUsageByMeterAndDate(dto);

        // Debugging - kiểm tra danh sách dữ liệu tháng trước
        System.out.println("Dữ liệu tháng trước: " + lastMonthUsage);

        if (lastMonthUsage != null && !lastMonthUsage.isEmpty()) {
            // Nếu có dữ liệu tháng trước, lấy chỉ số điện cuối kỳ từ bản ghi cuối cùng
            BigDecimal lastMonthEndReading = lastMonthUsage.get(lastMonthUsage.size() - 1).getEndReading();
            BigDecimal startReading = lastMonthEndReading != null ? lastMonthEndReading : BigDecimal.ZERO;
            dto.setStartReading(startReading); // Gán chỉ số đầu kỳ cho DTO

            // Tính toán lượng điện sử dụng
            BigDecimal usageAmount = dto.getEndReading().subtract(startReading);
            dto.setUsageAmount(usageAmount);
        } else {
            // Nếu không có dữ liệu tháng trước (lần ghi đầu tiên)
            dto.setStartReading(BigDecimal.ZERO);

            // Tính toán lượng điện sử dụng nếu có giá trị endReading
            if (dto.getEndReading() != null) {
                dto.setUsageAmount(dto.getEndReading()); // Sử dụng endReading làm lượng sử dụng
            }
        }
        // Tính toán tiền điện
        BigDecimal usageAmount = dto.getUsageAmount() != null ? dto.getUsageAmount() : BigDecimal.ZERO;
        BigDecimal totalCost = electricityCostService.calculateCost(usageAmount);
        dto.setElectricityCost(totalCost);

        // Chuyển DTO sang Entity
        ElectricityUsage entity = electricityUsageMapper.toEntity(dto);

        // Lưu bản ghi mới vào cơ sở dữ liệu
        ElectricityUsage savedEntity = electricityUsageRepository.save(entity);

        // Debugging - kiểm tra entity đã lưu
        System.out.println("Entity đã lưu: " + savedEntity);

        // Tạo thông báo cho khách hàng xác nhận chỉ số điện
        createElectricityUsageVerification(savedEntity);

        // Chuyển entity đã lưu trở lại DTO và trả về
        return electricityUsageMapper.toDTO(savedEntity);
    }


    // Get All Electricity Usages
    public List<ElectricityUsageDTO> getAllElectricityUsages() {
        return electricityUsageRepository.findAll().stream()
                .map(electricityUsageMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get Electricity Usage by ID
    public ElectricityUsageDTO getElectricityUsageById(Integer id) {
        ElectricityUsage entity = electricityUsageRepository.findById(id)
                .orElse(null);
        return electricityUsageMapper.toDTO(entity);
    }

    // Update Electricity Usage
    public ElectricityUsageDTO updateElectricityUsage(Integer id, ElectricityUsageDTO dto) {
        ElectricityUsage existingEntity = electricityUsageRepository.findById(id)
                .orElse(null);

        electricityUsageMapper.updateEntity(dto, existingEntity);
        ElectricityUsage updatedEntity = electricityUsageRepository.save(existingEntity);
        return electricityUsageMapper.toDTO(updatedEntity);
    }

    // Delete Electricity Usage
    public void deleteElectricityUsage(Integer id) {
        ElectricityUsage existingEntity = electricityUsageRepository.findById(id)
                .orElse(null);
        electricityUsageRepository.delete(existingEntity);
    }

    private void createElectricityUsageVerification(ElectricityUsage savedUsage) {
        // Lấy dữ liệu tháng trước
        List<ElectricityUsage> lastMonthUsage = electricityUsageRepository.findByMeterIdAndReadingDateBetween(
                savedUsage.getMeter().getId(),
                savedUsage.getReadingDate().minusMonths(1).withDayOfMonth(1),
                savedUsage.getReadingDate().minusMonths(1).withDayOfMonth(savedUsage.getReadingDate().minusMonths(1).lengthOfMonth())
        );

        // Biến để lưu thông tin của tháng trước
        BigDecimal previousEndReading = BigDecimal.ZERO;
        BigDecimal previousMonthUsage = BigDecimal.ZERO;
        BigDecimal previousMonthCost = BigDecimal.ZERO;
        String previousMonthImageName = null;

        if (!lastMonthUsage.isEmpty()) {
            // Lấy bản ghi cuối cùng của tháng trước
            ElectricityUsage lastMonthRecord = lastMonthUsage.get(lastMonthUsage.size() - 1);
            previousEndReading = lastMonthRecord.getEndReading();
            previousMonthCost = lastMonthRecord.getElectricityCost();  // Sử dụng giá trị chi phí tháng trước
            previousMonthImageName = lastMonthRecord.getImageName();
            previousMonthUsage = lastMonthRecord.getUsageAmount();
            // Hình ảnh tháng trước
        }

        // Tạo DTO cho ElectricityUsageVerification
        ElectricityUsageVerificationDto verificationDto = new ElectricityUsageVerificationDto();
        verificationDto.setMeterId(savedUsage.getMeter().getId());
        verificationDto.setStartReading(previousEndReading); // Chỉ số cuối kỳ của tháng trước làm chỉ số đầu kỳ
        verificationDto.setEndReading(savedUsage.getEndReading());
        // Chỉ số cuối kỳ của tháng hiện tại
        verificationDto.setReadingDate(savedUsage.getReadingDate());


        verificationDto.setUsageAmountCurrentMonth(savedUsage.getUsageAmount());
        verificationDto.setUsageAmountPreviousMonth(previousMonthUsage);

        verificationDto.setStatus("UNACTIV"); // Trạng thái chờ xác nhận
        verificationDto.setCurrentMonthCost(savedUsage.getElectricityCost());
        verificationDto.setPreviousMonthCost(previousMonthCost);
        verificationDto.setImageName(savedUsage.getImageName()); // Ảnh của tháng hiện tại
        verificationDto.setPreviousMonthImageName(previousMonthImageName); // Ảnh của tháng trước

        // Gọi service tạo ElectricityUsageVerification
        ElectricityUsageVerificationDto save = electricityUsageVerificationService.createElectricityUsageVerification(verificationDto);
        Integer contacId = someFilterByMeterIdService.getContactIdFromMeterId(savedUsage.getMeter().getId());
        notificationPaymentContractService.sendElectricityUsageVerificationNotification(contacId, save);

    }

    public void deleteElectricityUsage(int id) {
        // Kiểm tra xem bản ghi có tồn tại hay không
        Optional<ElectricityUsage> optionalUsage = electricityUsageRepository.findById(id);
        if (optionalUsage.isPresent()) {
            // Xóa bản ghi
            electricityUsageRepository.deleteById(id);
            System.out.println("Đã xóa bản ghi với ID: " + id);
        } else {
            throw new EntityNotFoundException("Không tìm thấy bản ghi với ID: " + id);
        }
    }
}



