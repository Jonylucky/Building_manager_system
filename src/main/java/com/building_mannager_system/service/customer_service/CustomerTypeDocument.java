package com.building_mannager_system.service.customer_service;

import com.building_mannager_system.dto.requestDto.customer.CustomerTypeDocumentDto;
import com.building_mannager_system.entity.customer_service.customer_manager.CustomertypeDocument;
import com.building_mannager_system.mapper.customerMapper.CustomerTypeDocumentMapper;
import com.building_mannager_system.repository.Contract.CustomertypeRepository;
import com.building_mannager_system.repository.Contract.CustometypeDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerTypeDocument {


    @Autowired
    private CustomerTypeDocumentMapper customerTypeDocumentMapper;
    @Autowired
    private CustometypeDocumentRepository  custometypeDocumentRepository;
    public List<CustomerTypeDocumentDto> findByCustomerTypeAndStatus(Integer customerTypeId, boolean status) {
        return custometypeDocumentRepository.findByCustomerTypeIdAndStatus(customerTypeId, status)
                .stream()
                .map(customerTypeDocumentMapper::toDto)
                .collect(Collectors.toList());
    }



}
