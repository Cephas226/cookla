package com.oms.service;

import com.oms.model.Skill;
import com.oms.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillService {
  @Autowired
  private SkillRepository skillRepository;

  public Optional<Skill> findById(Long id){
    return skillRepository.findById(id);}

  public Skill saveSkill(Skill skill){
    return skillRepository.save(skill);
  }
  public void deleteSkill(Long Id){
    skillRepository.deleteById(Id);
  }
  public Page<Skill> findAll(Pageable pageable) {
    return skillRepository.findAll( pageable);
  }

}
