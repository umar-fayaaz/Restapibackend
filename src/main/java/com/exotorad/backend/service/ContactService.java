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

/**
 * Service class for managing contact identification and linking.
 */
@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	/**
	 * Identifies a contact by email or phone number. If no match is found, a new
	 * primary contact is created. If a match is found, the contact is updated as
	 * secondary.
	 *
	 * @param email       the contact's email
	 * @param phoneNumber the contact's phone number
	 * @return a formatted response map with contact details
	 */

	public Map<String, Object> identifyContact(String email, String phoneNumber) {
		Contact matchingContact = contactRepository.findByEmailOrPhoneNumber(email, phoneNumber);

		if (matchingContact == null) {
			// Create a new primary contact
			Contact newContact = new Contact();
			newContact.setEmail(email);
			newContact.setPhoneNumber(phoneNumber);
			newContact.setLinkPrecedence(LinkPrecedence.PRIMARY);
			contactRepository.save(newContact);

			return formatResponse(newContact);
		}

		else {
			// Update the matching contact as secondary
			matchingContact.setLinkPrecedence(LinkPrecedence.SECONDARY);
			contactRepository.save(matchingContact);
			return formatResponse(matchingContact);
		}
	}

	/**
	 * Formats the contact details into a response map.
	 *
	 * @param primaryContact the primary contact to format
	 * @return a map containing contact details
	 */

	private Map<String, Object> formatResponse(Contact primaryContact) {
		Map<String, Object> response = new HashMap();
		response.put("primaryContactId", primaryContact.getId());
		response.put("emails", List.of(primaryContact.getEmail()));
		response.put("phoneNumbers", List.of(primaryContact.getPhoneNumber()));
		response.put("LinkedPrecedence", primaryContact.getLinkPrecedence());

		return response;
	}
}
