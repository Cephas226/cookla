package com.oms.controller;

import com.oms.model.Room;
import com.oms.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    /*Selectionnez tous les courses*/
    @GetMapping("/classroom")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Page<Room> ListClassroom(Pageable pageable) {
        return classroomService.findAll(pageable);
    }

    @PostMapping("/classroom")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Room addClassroom(@RequestBody Room room){
        return classroomService.saveClassroom(room);
    }

    @GetMapping("/classroom/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Optional<Room> selectOneClassroom(@PathVariable ("id") Long Id){
        return classroomService.findById(Id);
    }
    @PutMapping("/classroom/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Optional<Room> updateClassroom (@RequestBody Room roomRequest, @PathVariable ("id") Long Id){
        return classroomService.findById(Id).map(classroom -> {
            classroom.setName(roomRequest.getName());
            classroom.setArchived(roomRequest.getArchived());
            return classroomService.saveClassroom(classroom);
        });
    }
    @PatchMapping("/classroom/{classroom_id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Optional<Room> patchEmployee(@RequestBody Room roomRequest, @PathVariable (value = "classroom_id") Long classroom_id) {
        return classroomService.findById(classroom_id).map(classroom -> {
            classroom.setArchived(roomRequest.getArchived());
            return classroomService.saveClassroom(classroom);
        });
    }
    @DeleteMapping("/classroom/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public void deleteClassroom(@PathVariable ("id")Long Id){
        classroomService.deleteClassroom(Id);
    }
}
