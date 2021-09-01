package com.oms.service;

import java.util.Optional;

import com.oms.model.Student;
import com.oms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  @Autowired
  StudentRepository studentRepository;

  public Page<Student> findAllStudent (Pageable pageable){
    return  studentRepository.findAll(pageable);
  }
  public Student saveStudent(Student student){
    return  studentRepository.save(student);
  }
  public void deleteStudent(Long code){
    studentRepository.deleteById(code);
  }
  public Boolean existsByEmail (String email){
    return studentRepository.existsByEmail(email);
  }
  public Optional<Student> findStudentByCode(Long code){
    return studentRepository.findStudentByCode(code);
  }
}
