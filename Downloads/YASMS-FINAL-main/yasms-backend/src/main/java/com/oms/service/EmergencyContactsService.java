package com.oms.service;

import com.oms.model.EmergencyContact;
import com.oms.repository.EmergencyContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmergencyContactsService {
  @Autowired
  private EmergencyContactsRepository emergencyContactsRepository;

  public Optional<EmergencyContact> findById(Long id){
    return emergencyContactsRepository.findById(id);}

  public EmergencyContact saveEmergencyContact(EmergencyContact emergencyContacts){
    return emergencyContactsRepository.save(emergencyContacts);
  }
  public Page<EmergencyContact> findAll(Pageable pageable) {
    return emergencyContactsRepository.findAll( pageable);
  }

}
