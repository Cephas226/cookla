package com.oms.service;

import com.oms.model.Employee;
import com.oms.model.Historique;
import com.oms.repository.HistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HistoriqueService {

  @Autowired
  private HistoriqueRepository historiqueRepository;

  public Page<Historique> findAll(Pageable pageable) {
    return historiqueRepository.findAll( pageable);
  }

  public Historique saveHistorique(Historique historique) {
    return historiqueRepository.save(historique);
  }
}
