package com.building_mannager_system.service.customer_service;

import com.building_mannager_system.dto.requestDto.customer.CustomertypeDto;
import com.building_mannager_system.entity.customer_service.customer_manager.Customertype;
import com.building_mannager_system.mapper.customerMapper.CustomerTypesMapper;
import com.building_mannager_system.repository.Contract.CustomertypeRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerTypeService {

    @Autowired
    private CustomertypeRepository customertypeRepository;
    @Autowired
    private CustomerTypesMapper customerTypesMapper;


    public   List<CustomertypeDto> findAll() {
        return customerTypesMapper.toDTOList(customertypeRepository.findAll()) ;
    }


}
