package com.oms.service;

import com.oms.model.ERole;
import com.oms.model.Role;
import com.oms.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleService {
  @Autowired
  private RoleRepository roleRepository;

  public Optional<Role> findByName (ERole name){
    return roleRepository.findByName(name);
  }
}
