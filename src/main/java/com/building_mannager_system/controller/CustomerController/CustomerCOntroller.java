package com.building_mannager_system.controller.CustomerController;


import com.building_mannager_system.dto.requestDto.customer.CustomerDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.service.customer_service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customer")
public class CustomerCOntroller {

    @Autowired
    private CustomerService customerService;
    @PostMapping
    public ResponseEntity<ApiResponce<CustomerDto>> createCustomer(@RequestBody CustomerDto customerDto) {
        try {
            // Call the service to create the customer
            CustomerDto createdCustomer = customerService.createCustomer(customerDto);

            // Wrap the response in ApiResponse and return with HTTP status 201 (Created)
            ApiResponce<CustomerDto> response = new ApiResponce<>(200, createdCustomer, "Customer created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle exception and return a bad request response if something goes wrong
            ApiResponce<CustomerDto> response = new ApiResponce<>(500, null, "Error creating customer: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
