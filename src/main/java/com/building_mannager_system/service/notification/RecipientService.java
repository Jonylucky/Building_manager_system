package com.building_mannager_system.service.notification;

import com.building_mannager_system.dto.requestDto.verificationDto.RecipientDto;
import com.building_mannager_system.entity.notification.Recipient;
import com.building_mannager_system.mapper.notification.RecipientMapper;
import com.building_mannager_system.repository.notification.RecipientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipientService {



    @Autowired
    private RecipientRepository recipientRepository;
    @Autowired
    private RecipientMapper recipientMapper;
    public Recipient createRecipient(RecipientDto recipientDto) {

        Recipient recipient = recipientMapper.toEntity(recipientDto);

        recipient = recipientRepository.save(recipient);

        return   recipient;
    }


        public List<Recipient> getRecipientByReferenceId(Integer referenceId) {
            return recipientRepository.findByReferenceId(referenceId);

        }


}
