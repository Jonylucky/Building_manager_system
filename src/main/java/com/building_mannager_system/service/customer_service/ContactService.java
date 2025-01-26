package com.building_mannager_system.service.customer_service;

import com.building_mannager_system.dto.requestDto.customer.ContactDto;
import com.building_mannager_system.entity.customer_service.contact_manager.Contract;
import com.building_mannager_system.entity.customer_service.customer_manager.Contact;
import com.building_mannager_system.mapper.customerMapper.ContactMapper;
import com.building_mannager_system.repository.Contract.ContactRepository;
import com.building_mannager_system.repository.Contract.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactMapper contactMapper;

    public ContactDto createContact(ContactDto contactDto) {
        // Convert DTO to Entity
        Contact contact = contactMapper.toEntity(contactDto);

        // Persist the entity (save to the database)
        Contact savedContact = contactRepository.save(contact);

        // Convert the saved entity back to DTO and return it
        return contactMapper.toDTO(savedContact);
    }


    // Phương thức lấy Contact từ CustomerId
    public Contact getContactByCustomerId(Integer customerId) {
        // Tìm Contact theo CustomerId
        Optional<Contact> contactOptional = contactRepository.findByCustomerId(customerId);
        if (!contactOptional.isPresent()) {
            throw new RuntimeException("Contact not found for CustomerId: " + customerId);
        }
        return contactOptional.get();
    }
    public  Contact getContactById(Integer contactId) {
        Optional<Contact> contactOptional = contactRepository.findById(contactId);
        if (!contactOptional.isPresent()) {
            throw new RuntimeException("Contact not found for ContactId: " + contactId);
        }
        return contactOptional.get();
    }

}
