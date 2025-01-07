package com.exotorad.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exotorad.backend.service.ContactService;

/**
 * Rest Controller for handling contact-related API requests. This controller
 * processes incoming requests to identify and manage contacts.
 */

@RestController
@RequestMapping("/api") // Base URL for all endpoints in this
public class ContactController {

	// Injecting the ContactService dependency to handle business logic
	@Autowired
	private ContactService contactService;

	/**
	 * Endpoint to identify a contact based on the provided email and phone number.
	 *
	 * @param payload A JSON payload containing "email" and "phoneNumber" fields.
	 * @return A ResponseEntity containing the result of contact identification. The
	 *         response includes consolidated contact details.
	 */

	@PostMapping("/identify") // Maps HTTP POST requests to this method
	public ResponseEntity<Map<String, Object>> identifyContact(@RequestBody Map<String, String> payload) {
		String email = payload.get("email");
		String phoneNumber = payload.get("phoneNumber");
		// Call the service layer to handle the contact identification logic

		Map<String, Object> response = contactService.identifyContact(email, phoneNumber);
		// Return the response wrapped in an HTTP 200 (OK) status

		return ResponseEntity.ok(response);
	}
}
