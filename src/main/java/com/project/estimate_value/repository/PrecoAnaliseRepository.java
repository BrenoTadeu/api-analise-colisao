package com.project.estimate_value.repository;

import com.project.estimate_value.model.PrecoAnalise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrecoAnaliseRepository extends JpaRepository<PrecoAnalise, Long> {
    Optional<PrecoAnalise> findByComponenteAndTipoDano(String componente, String tipoDano);
}
