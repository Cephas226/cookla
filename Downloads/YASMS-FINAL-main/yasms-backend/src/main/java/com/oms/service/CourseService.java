package com.oms.service;

import com.oms.model.Course;
import com.oms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Optional<Course> findById(Long id){
        return courseRepository.findById(id);
    }

    public List<Course> findByCourseLevel(String courseLevel){
        return courseRepository.findCourseByCourseLevel(courseLevel);
    }

    public Course saveCourse(Course courses){
        return courseRepository.save(courses);
    }
    public Page<Course> findAll(Pageable pageable) {
        return courseRepository.findAll( pageable);
    }
    public void deleteCourse(Long Id){
        courseRepository.deleteById(Id);
    }
}
