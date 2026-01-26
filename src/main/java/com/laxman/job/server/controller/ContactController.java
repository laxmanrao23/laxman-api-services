package com.laxman.job.server.controller;


import com.laxman.job.server.model.Contact;
import com.laxman.job.server.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {
    @Autowired
    private ContactRepository contactRepo;

    @PostMapping("/submit")
    public Map<String, String> submit(@RequestBody Contact contact) {
        contactRepo.save(contact);
        return Map.of("message","Message received successfully");
    }

    @GetMapping("/all")
    public List<Contact> getAllContacts() {
        return contactRepo.findAll();
    }
}
