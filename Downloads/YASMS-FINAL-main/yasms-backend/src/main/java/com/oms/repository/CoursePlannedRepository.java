package com.oms.repository;

import com.oms.model.Employee;
import com.oms.model.PlannedCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CoursePlannedRepository extends JpaRepository <PlannedCourse,Long> {

}
