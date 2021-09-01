package com.oms.repository;

import com.oms.model.Historique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoriqueRepository extends JpaRepository <Historique, Long> {
}
