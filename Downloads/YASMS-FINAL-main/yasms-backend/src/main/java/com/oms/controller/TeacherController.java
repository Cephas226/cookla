package com.oms.controller;

import com.oms.model.Teachers;
import com.oms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /*Selectionnez tous les courses*/
    @GetMapping("/teachers")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Page<Teachers> ListTeacher(Pageable pageable) {
        return teacherService.findAll(pageable);
    }

    @PostMapping("/teachers")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Teachers addTeacher(@RequestBody Teachers teachers){
        return teacherService.saveTeacher(teachers);
    }

 @GetMapping("/teachers/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Optional<Teachers> selectOneTeacher(@PathVariable ("id") Long Id){
        return teacherService.findById(Id);
    }
    @PutMapping("/teachers/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Optional<Teachers> updateTeacher (@RequestBody Teachers teacherRequest, @PathVariable ("id") Long Id){
        return teacherService.findById(Id).map(teacher -> {
            teacher.setFirstName(teacherRequest.getFirstName());
            teacher.setLastName(teacherRequest.getLastName());
            teacher.setJobRole(teacherRequest.getJobRole());
            teacher.setPrivatePhone(teacherRequest.getPrivatePhone());
            teacher.setGender(teacherRequest.getGender());
            teacher.setEmail(teacherRequest.getEmail());
            return teacherService.saveTeacher(teacher);
        });
    }
    @PatchMapping("/teachers/{teacher_id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Optional<Teachers> patchTeacher(@RequestBody Teachers teachersRequest, @PathVariable (value = "teacher_id") Long teacher_id) {
        return teacherService.findById(teacher_id).map(teacher -> {
            teacher.setArchived(teachersRequest.getArchived());
            return teacherService.saveTeacher(teacher);
        });
    }
    /*@DeleteMapping("/teachers/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public void deleteTeacher(@PathVariable ("id")Long Id){
        teacherService.deleteTeacher(Id);
    }*/
}
