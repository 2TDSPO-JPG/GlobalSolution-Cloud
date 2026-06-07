package com.fiap.astrocolony.lodistics.spring.repository;

import com.fiap.astrocolony.lodistics.spring.entity.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Long> {}