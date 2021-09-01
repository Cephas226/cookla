package com.oms.repository;

import com.oms.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CourseRepository extends JpaRepository <Course,Long> {
    Optional<Course> findCoursesByCourseName(String courseName);
    @Query("SELECT c FROM Course c where c.courseLevel = :courseLevel")
    List<Course> findCourseByCourseLevel(@Param("courseLevel") String courseLevel);
}
