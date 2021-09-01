package com.oms.controller;

import com.oms.model.Historique;
import com.oms.service.EmployeeService;
import com.oms.service.HistoriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api")
public class HistoriqueController {

  @Autowired
  HistoriqueService historiqueService;

  @Autowired
  EmployeeService employeeService;

  @GetMapping("/historique")
  public Page findAllHistorique(Pageable pageable){
    return historiqueService.findAll(pageable);
  }
  @PostMapping("/employee/{employee_id}/historique")
  public Optional<Historique> postHistorique(@RequestBody Historique historiqueRequest, @PathVariable (value = "employee_id") Long employee_id) {
    return employeeService.findById(employee_id).map(employee -> {
      historiqueRequest.setEmployee(employee);
      return historiqueService.saveHistorique(historiqueRequest);
    });
  }
  @PatchMapping("/employee/{employee_id}/historique")
  public Optional<Historique> updateHistory(@RequestBody Historique historiqueRequest, @PathVariable (value = "employee_id") Long employee_id) {
    return employeeService.findById(employee_id).map(employee -> {
      historiqueRequest.setEmployee(employee);
      return historiqueService.saveHistorique(historiqueRequest);
    });
  }
}
