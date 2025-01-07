package com.exotorad.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exotorad.backend.dto.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
	Contact findByEmailOrPhoneNumber(String email, String phoneNumber);
}
