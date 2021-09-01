package com.oms.controller;

import com.oms.model.Institution;
import com.oms.model.Skill;
import com.oms.service.InstitutionService;
import com.oms.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api")
public class InstitutionController {
    @Autowired
    private InstitutionService institutionService;

    @GetMapping("/institution")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Page<Institution> ListInstitution(Pageable pageable) {
        return institutionService.findAll(pageable);
    }
    @PostMapping("/institution")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Institution addInstitution(@RequestBody Institution institution){
        return institutionService.saveInstitution(institution);
    }
    @GetMapping("/institution/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Optional<Institution> selectOneInstitution(@PathVariable ("id") Long Id){
        return institutionService.findById(Id);
    }
    @DeleteMapping("/institution/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('USER')")
    public void deleteInstitution(@PathVariable ("id")Long Id){
        institutionService.deleteInstitution(Id);
    }
}
