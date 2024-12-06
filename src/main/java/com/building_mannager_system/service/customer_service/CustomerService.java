package com.building_mannager_system.service.customer_service;

import com.building_mannager_system.dto.requestDto.customer.CustomerDto;
import com.building_mannager_system.dto.requestDto.customer.CustomertypeDto;
import com.building_mannager_system.entity.customer_service.customer_manager.Customer;
import com.building_mannager_system.mapper.customerMapper.CustomerMapper;
import com.building_mannager_system.repository.Contract.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    public CustomerDto createCustomer(CustomerDto customerDto) {

        Customer customer = customerMapper.toEntity(customerDto);
        // Save the Customer entity in the database
        Customer savedCustomer = customerRepository.save(customer);

        // Convert the saved Customer entity back to CustomerDto
        return customerMapper.toDto(savedCustomer);
    }
//    public List<CustomerDto> getAllCustomers() {
//        List<Customer> customers = customerRepository.findAll();
//        return  customerMapper.to
//
//    }

    public List<CustomerDto> checkBirthDay(){
        // Lấy ngày hôm nay
        LocalDate today = LocalDate.now();

        // Lấy ngày trong 3 ngày tới
        LocalDate targetDate = today.plusDays(3);

        // Lọc các khách hàng có sinh nhật trong 3 ngày tới
        List<CustomerDto> customersWithUpcomingBirthdays = customerRepository.findAll().stream()
                .filter(customer -> customer.getBirthday() != null)
                .filter(customer -> isBirthdayInNextThreeDays(customer.getBirthday(), today, targetDate))
                .map(customerMapper::toDto)  // Sử dụng Mapper để chuyển đổi Customer thành CustomerDto
                .collect(Collectors.toList());

        return customersWithUpcomingBirthdays;

    }
    // Kiểm tra xem sinh nhật có trong 3 ngày tới không
    private boolean isBirthdayInNextThreeDays(LocalDate birthday, LocalDate today, LocalDate targetDate) {
        // Chỉ so sánh ngày và tháng, không xét năm
        LocalDate nextBirthday = birthday.withYear(today.getYear());

        // Nếu ngày sinh nhật đã qua trong năm nay, lấy sinh nhật của năm sau
        if (nextBirthday.isBefore(today)) {
            nextBirthday = nextBirthday.withYear(today.getYear() + 1);
        }

        // Kiểm tra xem ngày sinh nhật có trong khoảng từ ngày hôm nay đến 3 ngày tới không
        return !nextBirthday.isBefore(today) && !nextBirthday.isAfter(targetDate);
    }
}
