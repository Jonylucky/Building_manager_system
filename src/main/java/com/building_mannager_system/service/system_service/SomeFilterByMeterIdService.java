package com.building_mannager_system.service.system_service;

import com.building_mannager_system.entity.customer_service.contact_manager.Contract;
import com.building_mannager_system.entity.customer_service.contact_manager.Office;
import com.building_mannager_system.entity.customer_service.customer_manager.Contact;
import com.building_mannager_system.service.customer_service.ContactService;
import com.building_mannager_system.service.customer_service.ContractService;
import com.building_mannager_system.service.customer_service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SomeFilterByMeterIdService {
    @Autowired
    private MeterService meterService;  // Tiêm MeterService
    @Autowired
    private ContractService contractService;  // Tiêm ContractService

    @Autowired
    private ContactService contactService;

    // Phương thức lấy ContactId từ MeterId
    public Integer getContactIdFromMeterId(Integer meterId) {
        // Tìm Office từ MeterId
        Office office = meterService.getOfficeByMeterId(meterId);

        // Tìm Contract từ OfficeId
        Contract contract = contractService.getContractByOfficeId(office.getId());

        // Lấy CustomerId từ Contract
        Integer customerId = contract.getCustomerID().getId();

        // Lấy ContactId từ CustomerId
        Contact contact = contactService.getContactByCustomerId(customerId);

        return contact.getId();  // Trả về ContactId
    }
}
