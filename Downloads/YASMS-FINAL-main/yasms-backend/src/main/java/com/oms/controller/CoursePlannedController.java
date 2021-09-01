package com.oms.controller;

import com.oms.model.Employee;
import com.oms.model.PlannedCourse;
import com.oms.service.CoursePlannedService;
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
public class CoursePlannedController {

    @Autowired
    private CoursePlannedService coursePlannedService;


    @Autowired
    private TeacherService teacherService;

    /*Selectionnez tous les courses*/
    @GetMapping("/coursesPlanned")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Page<PlannedCourse> ListPlannedCourses(Pageable pageable) {
        return coursePlannedService.findAll(pageable);
    }

    @PostMapping("/coursesPlanned")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public PlannedCourse addPlannedCourses(@RequestBody PlannedCourse plannedCourse){
        System.out.println(plannedCourse.toString());
        return coursePlannedService.savePlannedCourse(plannedCourse);
    }

    @GetMapping("/coursesPlanned/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Optional<PlannedCourse> selectOnePlannedCourses(@PathVariable ("id") Long Id){
        return coursePlannedService.findById(Id);
    }
    @PutMapping("/coursesPlanned")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public PlannedCourse updateCoursePlanned (@RequestBody PlannedCourse coursePlannedRequest){
        return coursePlannedService.savePlannedCourse(coursePlannedRequest);
    }
    @PatchMapping("/coursesPlanned/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Optional<PlannedCourse> TupdateCoursePlanned (@RequestBody PlannedCourse coursePlannedRequest,
                                                         @PathVariable ("id") Long Id){
    return  coursePlannedService.findById(Id).map(c->{
            c.setCourseMode(coursePlannedRequest.getCourseMode());
            c.setCourseFrequency(coursePlannedRequest.getCourseFrequency());
            c.setCourseType(coursePlannedRequest.getCourseType());
            c.setEndDate(coursePlannedRequest.getEndDate());
            c.setStartDate(coursePlannedRequest.getStartDate());
            c.setStartTime(coursePlannedRequest.getStartTime());
            c.setEndTime(coursePlannedRequest.getEndTime());
            c.setPlannedCourseStatus(coursePlannedRequest.getPlannedCourseStatus());
            c.setStudents(coursePlannedRequest.getStudents());
            c.setRoom(coursePlannedRequest.getRoom());
            c.setEmployee(coursePlannedRequest.getEmployee());
            c.setRemark(coursePlannedRequest.getRemark());
        return coursePlannedService.savePlannedCourse(coursePlannedRequest);
        });
    }
    @DeleteMapping("/coursesPlanned/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public void deleteCourse(@PathVariable ("id")Long Id){
        coursePlannedService.deletePlannedCourse(Id);
    }

}
