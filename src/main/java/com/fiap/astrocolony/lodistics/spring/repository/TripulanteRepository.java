package com.fiap.astrocolony.lodistics.spring.repository;

import com.fiap.astrocolony.lodistics.spring.dto.TripulanteDto;
import com.fiap.astrocolony.lodistics.spring.entity.Tripulante;
import com.fiap.astrocolony.lodistics.spring.enuns.StatusTripulante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TripulanteRepository extends JpaRepository<Tripulante, Long> {
    List<TripulanteDto> findTripulanteByMissao_IdMissao(Long missaoIdMissao);
    Tripulante findTripulanteByPin(String pin);
    Optional<Tripulante> findTripulanteByPinAndSenha(String pin, String senha);
    Page<Tripulante> findByStatusTripulante(StatusTripulante statusTripulante, Pageable pageable);
}
