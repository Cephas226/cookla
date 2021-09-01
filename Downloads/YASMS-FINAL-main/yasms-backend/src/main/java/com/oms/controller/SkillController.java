package com.oms.controller;

import com.oms.model.EmergencyContact;
import com.oms.model.Skill;
import com.oms.service.EmergencyContactsService;
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
public class SkillController {
    @Autowired
    private SkillService skillService;

    @GetMapping("/skills")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Page<Skill> ListSkill(Pageable pageable) {
        return skillService.findAll(pageable);
    }
    @PostMapping("/skills")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Skill addSkill(@RequestBody Skill skill){
        return skillService.saveSkill(skill);
    }
    @GetMapping("/skills/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Optional<Skill> selectOneSkill(@PathVariable ("id") Long Id){
        return skillService.findById(Id);
    }
    @DeleteMapping("/skills/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('USER')")
    public void deleteStudent(@PathVariable ("id")Long Id){
        skillService.deleteSkill(Id);
    }
}
