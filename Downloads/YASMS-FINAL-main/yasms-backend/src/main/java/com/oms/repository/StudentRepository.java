package com.oms.repository;

import com.oms.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oms.model.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
  Optional<Student> findStudentByCode(Long code);
  Optional<Student> findByEmail(String email);
  Boolean existsByEmail(String email);
}
