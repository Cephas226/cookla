package com.oms.service;

import com.oms.model.Institution;
import com.oms.model.Skill;
import com.oms.repository.InstitutionRepository;
import com.oms.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstitutionService {
  @Autowired
  private InstitutionRepository institutionRepository;

  public Optional<Institution> findById(Long id){
    return institutionRepository.findById(id);}

  public Institution saveInstitution(Institution institution){
    return institutionRepository.save(institution);
  }
  public void deleteInstitution(Long Id){
    institutionRepository.deleteById(Id);
  }
  public Page<Institution> findAll(Pageable pageable) {
    return institutionRepository.findAll( pageable);
  }

}
