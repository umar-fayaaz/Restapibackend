package com.exotorad.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exotorad.backend.service.ContactService;

@RestController
@RequestMapping("/api")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/identify")
    public ResponseEntity<Map<String, Object>> identifyContact(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String phoneNumber = payload.get("phoneNumber");

        Map<String, Object> response = contactService.identifyContact(email, phoneNumber);

        return ResponseEntity.ok(response);
    }
}
