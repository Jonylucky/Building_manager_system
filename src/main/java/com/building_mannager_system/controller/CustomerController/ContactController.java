package com.building_mannager_system.controller.CustomerController;

import com.building_mannager_system.dto.requestDto.customer.ContactDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.service.customer_service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<ApiResponce<ContactDto>> createContact(@RequestBody ContactDto contactDto) {
        ApiResponce<ContactDto> response;
        try {
            // Attempt to create the contact using the service
            ContactDto createdContact = contactService.createContact(contactDto);

            // Return success response
            response = new ApiResponce<>(200, createdContact, "Success");
            return new ResponseEntity<>(response, HttpStatus.CREATED); // 201 status for created resource
        } catch (Exception e) {
            // Catch any exception that occurs during contact creation
            response = new ApiResponce<>(500, null, "Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // 500 status for errors
        }
    }
}
