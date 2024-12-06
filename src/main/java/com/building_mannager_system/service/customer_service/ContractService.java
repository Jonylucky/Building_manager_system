package com.building_mannager_system.service.customer_service;


import com.building_mannager_system.dto.requestDto.ContractDto.ContractDto;
import com.building_mannager_system.dto.requestDto.customer.CustomerTypeDocumentDto;
import com.building_mannager_system.dto.responseDto.ContractReminderDto;
import com.building_mannager_system.entity.customer_service.contact_manager.Contract;
import com.building_mannager_system.entity.customer_service.contact_manager.Office;
import com.building_mannager_system.entity.customer_service.customer_manager.Customer;
import com.building_mannager_system.entity.customer_service.customer_manager.Customertype;
import com.building_mannager_system.mapper.contractMapper.ContractMapper;
import com.building_mannager_system.repository.Contract.ContractRepository;
import com.building_mannager_system.service.ConfigService.FileService;
import com.building_mannager_system.service.officeAllcation.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class ContractService {



     @Autowired
     private ContractRepository contractRepository;
     @Autowired
     private OfficeService officeService;
     @Autowired
     private FileService fileService;

    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private CustomerTypeDocument customerTypeDocumentService;


    public List<ContractDto> getAllContracts() {
        List<Contract> contracts = contractRepository.findAll();
        return contracts.stream()
                .map(contractMapper::toDto) // Map each Contract to ContractDto
                .collect(Collectors.toList());
    }
    //crate ctract
    public  ContractDto createContract(ContractDto contractDto, MultipartFile file) {

        // Retrieve the office details using officeId

        Office office = officeService.findOfficeById(contractDto.getOfficeId());




        // Parse the dates
        LocalDate startDate = contractDto.getStartDate();
        LocalDate endDate = contractDto.getEndDate();

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null.");
        }

        // Calculate the duration in months between the start and end date
        long monthsBetween = ChronoUnit.MONTHS.between(startDate, endDate);

        // Calculate totalAmount = duration * (area * rent_price * service_fee)
        BigDecimal area = office.getArea();
        BigDecimal rentPrice = office.getRentPrice();
        BigDecimal serviceFee = office.getServiceFee();

        BigDecimal totalAmount = area
                .multiply(rentPrice)
                .multiply(serviceFee)
                .multiply(BigDecimal.valueOf(monthsBetween));

        // Set the totalAmount in the contract DTO
        contractDto.setTotalAmount(totalAmount);
        // Save the file if provided
        // Save the file if provided
        if (file != null && !file.isEmpty()) {
            try {
                String fileName = fileService.saveFile(file); // Use FileService to handle file saving
                contractDto.setFileName(fileName); // Set the unique file name in the contract DTO
            } catch (IOException e) {
                throw new RuntimeException("Error saving contract file: " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("File must not be empty.");
        }


        // Convert the DTO to the entity
        Contract contract = contractMapper.toEntity(contractDto);

        // Save the contract to the repository
        contract = contractRepository.save(contract);

        // Convert back to DTO and return
        return contractMapper.toDto(contract);
    }

      //check contractEnddate
     public List<ContractDto> checkEndDateContract(){
         // Lấy ngày hôm nay
         LocalDate today = LocalDate.now();

         // Lọc các hợp đồng có ngày kết thúc trong 1 tháng trước
         List<ContractDto> contractsWithEndDateInLastMonth = contractRepository.findAll().stream()
                 .filter(contract -> contract.getEndDate() != null)
                 .filter(contract -> isEndDateInOneMonth(contract.getEndDate(), today))
                 .map(contractMapper::toDto)  // Chuyển đổi Contract thành ContractDto
                 .collect(Collectors.toList());

         return contractsWithEndDateInLastMonth;
     }
     //check time contract
    private boolean isEndDateInOneMonth(LocalDate endDate, LocalDate today) {
        // Tính toán ngày kết thúc trong 1 tháng nữa từ hôm nay
        LocalDate oneMonthLater = today.plusMonths(1);

        // Kiểm tra xem ngày kết thúc có nằm trong khoảng từ hôm nay đến 1 tháng sau không
        return !endDate.isBefore(today) && !endDate.isAfter(oneMonthLater);
    }

//    checkbirthday Customer by COntract
    public List<ContractDto> checkCustomerBirthday() {
        // Lấy ngày hôm nay
        LocalDate today = LocalDate.now();

        // Lấy ngày trong 3 ngày tới
        LocalDate targetDate = today.plusDays(3);

        // Lọc các hợp đồng có khách hàng có sinh nhật trong 3 ngày tới
        List<ContractDto> contractsWithCustomerBirthdayInNextThreeDays = contractRepository.findAll().stream()
                .filter(contract -> contract.getCustomerID() != null && contract.getCustomerID().getBirthday() != null) // Kiểm tra có customer và birthday
                .filter(contract -> isCustomerBirthdayInNextThreeDays(contract.getCustomerID(), today, targetDate)) // Kiểm tra sinh nhật trong 3 ngày tới
                .map(contractMapper::toDto)  // Chuyển đổi Contract thành ContractDto
                .collect(Collectors.toList());

        return contractsWithCustomerBirthdayInNextThreeDays;
    }

    private boolean isCustomerBirthdayInNextThreeDays(Customer customer, LocalDate today, LocalDate targetDate) {
        LocalDate birthdayThisYear = customer.getBirthday().withYear(today.getYear());
System.out.println("birthday :  " + birthdayThisYear);
        // Nếu sinh nhật đã qua trong năm nay, kiểm tra năm sau
        if (birthdayThisYear.isBefore(today)) {
            birthdayThisYear = birthdayThisYear.plusYears(1);
        }

        // Kiểm tra xem sinh nhật của khách hàng có trong 3 ngày tới không
        return !birthdayThisYear.isBefore(today) && !birthdayThisYear.isAfter(targetDate);
    }



    /**
     * Kiểm tra và nhắc nhở lịch hợp đồng dựa trên trạng thái của CustomerTypeDocument.
     */
    public List<ContractReminderDto> checkInactiveContractsAndDocuments() {
        return contractRepository.findAll().stream()
                // Lọc hợp đồng có customer hợp lệ
                .filter(contract -> contract.getCustomerID() != null
                        && contract.getCustomerID().getId() != null)

                // Lọc hợp đồng có customerType hợp lệ
                .filter(contract -> contract.getCustomerID().getCustomerType() != null
                        && contract.getCustomerID().getCustomerType().getId() != null)

                // Kiểm tra các tài liệu liên quan có trạng thái "unactive"
                .filter(contract -> hasUnactiveDocuments(contract.getCustomerID().getCustomerType()))

                // Chuyển đổi hợp đồng và tài liệu thành DTO để nhắc nhở
                .map(contract -> createContractReminderDto(contract))

                // Thu thập kết quả thành danh sách
                .collect(Collectors.toList());
    }

    /**
     * Kiểm tra xem CustomerType có tài liệu nào "unactive" không.
     */
    private boolean hasUnactiveDocuments(Customertype customerType) {
        // Gọi service để lấy danh sách tài liệu với trạng thái "unactive"
        List<CustomerTypeDocumentDto> unactiveDocuments = customerTypeDocumentService
                .findByCustomerTypeAndStatus(customerType.getId(), false); // false = "unactive"

        return !unactiveDocuments.isEmpty(); // Trả về true nếu có tài liệu "unactive"
    }

    /**
     * Tạo DTO nhắc nhở hợp đồng.
     */
    private ContractReminderDto createContractReminderDto(Contract contract) {
        ContractReminderDto reminderDto = new ContractReminderDto();
        reminderDto.setContract(contractMapper.toDto(contract)); // Thông tin hợp đồng
        reminderDto.setCustomerTypeDocuments(
                customerTypeDocumentService.findByCustomerTypeAndStatus(
                        contract.getCustomerID().getCustomerType().getId(), false)); // Tài liệu "unactive"
        return reminderDto;
    }

    // Filter contract by ID with additional filtering logic
    public Contract filterContractById(Integer contractId) {
        // Retrieve the contract using the contractId
        System.out.println(contractId);
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new IllegalArgumentException("Contract not found with ID: " + contractId));

//        // Apply additional filters or conditions if needed
//        if (contract.getStartDate().isAfter(LocalDate.now())) {
//            throw new IllegalArgumentException("Contract start date is in the future.");
//        }
//
//        if (contract.getEndDate().isBefore(LocalDate.now())) {
//            throw new IllegalArgumentException("Contract has already ended.");
//        }
//
//        // If you need more specific filtering (e.g., customer status or contract type), apply here
//        if (contract.getCustomerID() == null || contract.getCustomerID().getStatus() != "Active") {
//            throw new IllegalArgumentException("Customer is not active for contract ID: " + contractId);
//        }

        // Return the filtered contract
        return contract;
    }


}
