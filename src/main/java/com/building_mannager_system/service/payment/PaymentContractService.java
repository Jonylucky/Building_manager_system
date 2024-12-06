package com.building_mannager_system.service.payment;

import com.building_mannager_system.dto.requestDto.paymentDto.PaymentContractDto;
import com.building_mannager_system.entity.customer_service.contact_manager.Contract;
import com.building_mannager_system.entity.pament_entity.PaymentContract;
import com.building_mannager_system.enums.PaymentStatus;
import com.building_mannager_system.mapper.paymentMapper.PaymentContractMapper;
import com.building_mannager_system.repository.paymentRepository.PaymentContractRepository;
import com.building_mannager_system.service.customer_service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PaymentContractService {

    @Autowired
    private PaymentContractRepository paymentContractRepository;
    @Autowired
    private PaymentContractMapper paymentContractMapper;
    @Autowired
    private ContractService contractService;
    public PaymentContractDto createPaymentContract(PaymentContractDto paymentDto) {
        System.out.println(paymentDto.getPaymentStatus());
        // Step 1: Retrieve the contract using the contractId from the DTO
        Contract contract = contractService.filterContractById(paymentDto.getContractId());



        // Step 2: Calculate the number of months between startDate and endDate of the contract
        long monthsBetween = ChronoUnit.MONTHS.between(contract.getStartDate(), contract.getEndDate());

        if (monthsBetween <= 0) {
            throw new IllegalArgumentException("Invalid contract dates. Start date must be before end date.");
        }

        // Step 3: Calculate the monthly payment amount
        BigDecimal monthlyPayment = contract.getTotalAmount().divide(BigDecimal.valueOf(monthsBetween), 2, RoundingMode.HALF_UP);
        
        // Step 4: Set the payment details from the DTO and calculated values
        PaymentContractDto paymentContractDto = new PaymentContractDto();
        paymentContractDto.setContractId(contract.getId());
        paymentContractDto.setCustomerId(contract.getCustomerID().getId());
        paymentContractDto.setPaymentAmount(monthlyPayment);
        paymentContractDto.setPaymentDate(paymentDto.getPaymentDate());
        paymentContractDto.setPaymentStatus(PaymentStatus.UNPAID.name());
        // Step 5: Save the PaymentContract entity

        PaymentContract paymentContract = paymentContractMapper.paymentDtoToPayment(paymentContractDto);
        PaymentContract savedPaymentContract = paymentContractRepository.save(paymentContract);

        // Step 6: Map the saved entity to DTO and return the DTO
        return paymentContractMapper.paymentToPaymentDto(savedPaymentContract);
    }
    //filter payment by contract id
    public  PaymentContractDto getpaymentContractByCustomerId(int  customerId) {
        // Lấy tất cả PaymentContracts
        List<PaymentContract> paymentContracts = paymentContractRepository.findAll();

        // Lọc theo customerId
        PaymentContract paymentContract = paymentContracts.stream()
                .filter(pc -> pc.getCustomer().getId() == customerId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No PaymentContract found for customerId: " + customerId));

        // Chuyển đổi PaymentContract sang PaymentContractDto
        return paymentContractMapper.paymentToPaymentDto(paymentContract);


    }
    public PaymentContractDto getPaymentContractByCustomerIdAndUnpaidStatus(int customerId) {
        // Lấy tất cả PaymentContracts
        List<PaymentContract> paymentContracts = paymentContractRepository.findAll();

        // Tìm PaymentContract thỏa mãn customerId và trạng thái Unpaid
        PaymentContract paymentContract = paymentContracts.stream()
                .filter(pc -> pc.getCustomer().getId() == customerId) // Kiểm tra customerId
                .filter(pc -> "UNPAID".equals(pc.getPaymentStatus().name())) // Kiểm tra trạng thái Unpaid
                .findFirst()
                .orElse(null); // Trả về null nếu không tìm thấy

        // Nếu không tìm thấy, trả về DTO rỗng
        if (paymentContract == null) {
            return new PaymentContractDto(); // Trả về DTO rỗng
        }

        // Chuyển đổi PaymentContract sang PaymentContractDto
        return paymentContractMapper.paymentToPaymentDto(paymentContract);
    }


}
