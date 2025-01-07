package com.exotorad.backend.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exotorad.backend.dto.Contact;
import com.exotorad.backend.dto.LinkPrecedence;
import com.exotorad.backend.repository.ContactRepository;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Map<String, Object> identifyContact(String email, String phoneNumber) {
        Contact matchingContact = contactRepository.findByEmailOrPhoneNumber(email, phoneNumber);

        if (matchingContact==null) {
            // Create a new primary contact
            Contact newContact = new Contact();
            newContact.setEmail(email);
            newContact.setPhoneNumber(phoneNumber);
            newContact.setLinkPrecedence(LinkPrecedence.PRIMARY);
            contactRepository.save(newContact);

            return formatResponse(newContact);
        }

        
        else {
        	 matchingContact.setLinkPrecedence(LinkPrecedence.SECONDARY);
        	 contactRepository.save(matchingContact);
        	 return formatResponse(matchingContact);
        }
    }

    private Map<String, Object> formatResponse(Contact primaryContact) {
        Map<String, Object> response = new HashMap();
        response.put("primaryContactId", primaryContact.getId());
        response.put("emails", List.of(primaryContact.getEmail()));
        response.put("phoneNumbers", List.of(primaryContact.getPhoneNumber()));
        response.put("LinkedPrecedence", primaryContact.getLinkPrecedence());
        
        return response;
    }
}
