package com.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oms.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  Optional<Employee> findByEmail(String username);
  Boolean existsByEmail(String email);
  @Query("SELECT e FROM Employee e where e.status = :status")
  List<Employee> findEmployeeByStatus(@Param("status") Boolean status);
}
