package com.fiap.astrocolony.lodistics.spring.service;

import com.fiap.astrocolony.lodistics.spring.dto.TripulanteDto;
import com.fiap.astrocolony.lodistics.spring.dto.function.LoginDto;
import com.fiap.astrocolony.lodistics.spring.dto.mapper.TripulanteMapper;
import com.fiap.astrocolony.lodistics.spring.dto.requests.TripulanteAtualizar;
import com.fiap.astrocolony.lodistics.spring.dto.requests.TripulanteRequestDto;
import com.fiap.astrocolony.lodistics.spring.entity.Tripulante;
import com.fiap.astrocolony.lodistics.spring.enuns.StatusTripulante;
import com.fiap.astrocolony.lodistics.spring.exception.exceptions.EntidadeNaoLocalizadaException;
import com.fiap.astrocolony.lodistics.spring.exception.exceptions.ErrorAutenticacaoException;
import com.fiap.astrocolony.lodistics.spring.exception.exceptions.RegraNegocioException;
import com.fiap.astrocolony.lodistics.spring.repository.TripulanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripulanteService {

    private final TripulanteRepository tripulanteRepository;

    public TripulanteDto salvar(TripulanteRequestDto dto) {
        Tripulante tripulante = Tripulante.builder()
                .nmTripulante(dto.nmTripulante())
                .pesoTripulante(dto.pesoTripulante())
                .altura(dto.altura())
                .senha(dto.senha())
                .statusTripulante(StatusTripulante.DISPONIVEL)
                .build();

        Tripulante tripulanteSalvo = tripulanteRepository.save(tripulante);
        return TripulanteMapper.toDto(tripulanteSalvo);
    }

    public TripulanteDto buscarPorPin(String pin) {
        Tripulante tripulante = tripulanteRepository.findTripulanteByPin(pin);
        if (tripulante == null) throw new RuntimeException("Tripulante não encontrado");
        return TripulanteMapper.toDto(tripulante);
    }

    public TripulanteDto mudarStatusTripulante(TripulanteAtualizar dto) {
        Tripulante tripulante = tripulanteRepository.findTripulanteByPin(dto.pin());
        if (tripulante == null) throw new RuntimeException("Tripulante não encontrado");
        if (dto.status().toUpperCase().equals("EM_MISSAO")) throw new RegraNegocioException("Status inválido para atualização");

        tripulante.setStatusTripulante(StatusTripulante.valueOf(dto.status().toUpperCase()));
        Tripulante tripulanteAtualizado = tripulanteRepository.save(tripulante);
        return TripulanteMapper.toDto(tripulanteAtualizado);
    }

    public void deletarPorPin(String pin) {
        Tripulante tripulante = tripulanteRepository.findTripulanteByPin(pin);
        if (tripulante == null) throw new RuntimeException("Tripulante não encontrado");
        tripulanteRepository.delete(tripulante);
    }

    public TripulanteDto login(LoginDto login){
        Optional<Tripulante> tripulante = tripulanteRepository.findTripulanteByPinAndSenha(login.getEmailOrPin(), login.getSenha());
        if(tripulante.isEmpty())throw new ErrorAutenticacaoException("Login ou senha inválidos");
        return TripulanteMapper.toDto(tripulante.get());
    }

    public Page<TripulanteDto> buscarTripulantePorStatus(String status, Pageable pageable){
        Page<Tripulante> tripulante = tripulanteRepository.findByStatusTripulante(StatusTripulante.valueOf(status.toUpperCase()), pageable);
        if (tripulante == null) throw new EntidadeNaoLocalizadaException("Não há tripulantes com o status: " + status);
        return tripulante.map(TripulanteMapper::toDto);
    }

}
