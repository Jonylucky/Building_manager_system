package com.building_mannager_system.controller.CustomerController;

import com.building_mannager_system.dto.requestDto.customer.CustomertypeDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.service.customer_service.CustomerTypeService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/customertyper")
public class CustomerTyperController {
    @Autowired
    private CustomerTypeService customerTypeService;


    @GetMapping
    public ApiResponce<List<CustomertypeDto>> getAllCustomertypes() {
        // Lấy danh sách Customertype từ service
        List<CustomertypeDto> customerTypes = customerTypeService.findAll();

        // Tạo phản hồi API
        ApiResponce<List<CustomertypeDto>> response = new ApiResponce<>();
        response.setCode(200);  // Mã trạng thái HTTP (200 - OK)
        response.setData(customerTypes);  // Dữ liệu trả về
        response.setStatus("Success");  // Trạng thái

        return response;  // Trả về phản hồi API
    }

}
