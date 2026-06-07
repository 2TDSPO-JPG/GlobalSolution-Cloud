package com.fiap.astrocolony.lodistics.spring.repository;

import com.fiap.astrocolony.lodistics.spring.entity.Missao;
import com.fiap.astrocolony.lodistics.spring.enuns.StatusMissao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, Long> {
    Page<Missao> findAllByStatusMissao(StatusMissao statusMissao, Pageable page);
}
