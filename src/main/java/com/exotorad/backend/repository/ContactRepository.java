package com.exotorad.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exotorad.backend.dto.Contact;

/**
 * Repository interface for performing database operations on {@link Contact}
 * entities.
 */
public interface ContactRepository extends JpaRepository<Contact, Integer> {

	/**
	 * Finds a contact by email or phone number.
	 *
	 * @param email       the email to search for
	 * @param phoneNumber the phone number to search for
	 * @return the matching {@link Contact}, or {@code null} if not found
	 */
	Contact findByEmailOrPhoneNumber(String email, String phoneNumber);
}
