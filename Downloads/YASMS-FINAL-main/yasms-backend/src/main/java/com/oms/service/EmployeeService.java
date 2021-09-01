package com.oms.service;

import java.util.List;
import java.util.Optional;

import com.oms.model.Employee;
import com.oms.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class EmployeeService {
/*
	List<Employee> getAllEmployees();
	Optional<Employee> getOneEmployee(Long id);
	void saveEmployee(Employee e);
	void updateEmployee(Employee e);
	void deleteEmployee(Long id);
	void deleteAllEmployees();
*/
	/*------*/
  @Autowired
  private EmployeeRepository employeeRepository;

  public Boolean existsByEmail (String email){
    return employeeRepository.existsByEmail(email);
  }
  public Employee saveEmployee(Employee employee){
    return employeeRepository.save(employee);
  }

  public Optional<Employee> findByEmail(String email) {
    return employeeRepository.findByEmail(email);
  }

  public List<Employee> findByStatus(Boolean status) {
    return employeeRepository.findEmployeeByStatus(status);
  }

  public Page<Employee> findAll(Pageable pageable) {
    return employeeRepository.findAll( pageable);
  }
  public Optional<Employee> findById(Long id){
    return employeeRepository.findById(id);
  }
  public void deleteById(Long id){ employeeRepository.deleteById(id); }
}
