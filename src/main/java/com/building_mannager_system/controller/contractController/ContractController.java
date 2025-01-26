package com.building_mannager_system.controller.contractController;

import com.building_mannager_system.dto.requestDto.ContractDto.ContractDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.entity.customer_service.system_manger.Meter;
import com.building_mannager_system.service.customer_service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;
    @PostMapping(value = "createContract",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ApiResponce<?> createContract(@RequestBody ContractDto contractDto, @RequestParam("file") MultipartFile file) {
        try {
            // Save the contract using the service
            ContractDto savedContract = contractService.createContract(contractDto,file);

            // Return a success response
            return new ApiResponce<>(200, savedContract, "Success");
        } catch (Exception e) {
            // Handle exceptions and return an error response
            return new ApiResponce<>(500, null, "Failed to create contract: " + e.getMessage());
        }

    }


}
