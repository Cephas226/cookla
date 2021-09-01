package com.oms.controller;

import com.oms.model.*;
import com.oms.service.CourseService;
import com.oms.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstitutionService institutionService;

    /*Selectionnez tous les courses*/
    @GetMapping("/courses")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Page<Course> ListCourses(Pageable pageable) {
        return courseService.findAll(pageable);
    }

    @GetMapping("/coursesLevel/{courseLevel}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('USER') or hasRole('ADMIN')")
    List<Course> findCourseByCourseLevel(@PathVariable("courseLevel") String courseName){
        return courseService.findByCourseLevel(courseName);
    };

    @PostMapping("/courses")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Course addCourse(@RequestBody Course coursesRequest){
        System.out.println(coursesRequest.toString());
        Course unRegistercourse = new Course(
                coursesRequest.getCourseName(),
                coursesRequest.getCourseLevel(),
                coursesRequest.getCourseUnit(),
                coursesRequest.getCoursePrice(),
                coursesRequest.getCourseStatus(),
                coursesRequest.getCourseComment(),
                coursesRequest.getCourseDescription(),
                coursesRequest.getInstitution()
        );
        return courseService.saveCourse(unRegistercourse);
    }

    @GetMapping("/courses/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Optional<Course> selectOneCourse(@PathVariable ("id") Long Id){
        return courseService.findById(Id);
    }

    /*@PutMapping("/courses/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Optional<Courses> updateCourse (@RequestBody Courses courseRequest, @PathVariable ("id") Long Id){
        return courseService.findById(Id).map(course -> {
            course.setCouresRoot(courseRequest.getCouresRoot());
            course.setCoursesName(courseRequest.getCoursesName());
            course.setCoursesUnit(courseRequest.getCoursesUnit());
            course.setCoursesType(courseRequest.getCoursesType());
            return courseService.saveCourse(course);
        });
    }*/
    @PutMapping("/courses/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Optional<Course> updateCourse (@RequestBody Course courseRequest, @PathVariable ("id") Long Id){
        System.out.println(courseRequest.getCourseComment().length());
        return courseService.findById(Id).map(course -> {
           course.setCourseLevel(courseRequest.getCourseLevel());
            course.setCourseName(courseRequest.getCourseName());
            course.setCourseUnit(courseRequest.getCourseUnit());
           course.setCourseComment(courseRequest.getCourseComment());
            course.setCourseDescription(courseRequest.getCourseDescription());
            course.setCoursePrice(courseRequest.getCoursePrice());
            return courseService.saveCourse(course);
        });
    }
    @PatchMapping("/courses/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public Optional<Course> patchCourse (@RequestBody Course courseRequest, @PathVariable ("id") Long Id){
        return courseService.findById(Id).map(course -> {
            course.setCourseStatus(courseRequest.getCourseStatus());
            return courseService.saveCourse(course);
        });
    }
    @DeleteMapping("/courses/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
    public void deleteCourse(@PathVariable ("id")Long Id){
        courseService.deleteCourse(Id);
    }
}
