package com.oms.controller;

import com.oms.model.EmergencyContact;
import com.oms.service.EmergencyContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api")
public class emergencyContactsController {
    @Autowired
    private EmergencyContactsService emergencyContactsService;

    @GetMapping("/emergency")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Page<EmergencyContact> ListEmergencyContact(Pageable pageable) {
        return emergencyContactsService.findAll(pageable);
    }
    @PostMapping("/emergency")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public EmergencyContact addEmergencyContact(@RequestBody EmergencyContact emergencyContacts){
        return emergencyContactsService.saveEmergencyContact(emergencyContacts);
    }
    @GetMapping("/emergency/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Optional<EmergencyContact> selectOneEmmergency(@PathVariable ("id") Long Id){
        return emergencyContactsService.findById(Id);
    }
}
