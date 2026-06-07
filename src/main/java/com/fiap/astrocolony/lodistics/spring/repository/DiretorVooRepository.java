package com.fiap.astrocolony.lodistics.spring.repository;

import com.fiap.astrocolony.lodistics.spring.entity.DiretorVoo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiretorVooRepository extends JpaRepository<DiretorVoo, Long> {
    Optional<DiretorVoo> findByEmailDiretorAndSenhaDiretor(String emailDiretor, String senhaDiretor);
}
