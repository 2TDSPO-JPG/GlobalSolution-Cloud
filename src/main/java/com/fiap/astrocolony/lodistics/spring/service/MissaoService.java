package com.fiap.astrocolony.lodistics.spring.service;

import com.fiap.astrocolony.lodistics.spring.dto.MissaoDto;
import com.fiap.astrocolony.lodistics.spring.dto.TripulanteDto;
import com.fiap.astrocolony.lodistics.spring.dto.mapper.MissaoMapper;
import com.fiap.astrocolony.lodistics.spring.dto.requests.MissaoRequestDto;
import com.fiap.astrocolony.lodistics.spring.entity.DiretorVoo;
import com.fiap.astrocolony.lodistics.spring.entity.Missao;
import com.fiap.astrocolony.lodistics.spring.entity.Tripulante;
import com.fiap.astrocolony.lodistics.spring.enuns.StatusMissao;
import com.fiap.astrocolony.lodistics.spring.enuns.StatusTripulante;
import com.fiap.astrocolony.lodistics.spring.exception.exceptions.EntidadeNaoLocalizadaException;
import com.fiap.astrocolony.lodistics.spring.exception.exceptions.RegraNegocioException;
import com.fiap.astrocolony.lodistics.spring.repository.DiretorVooRepository;
import com.fiap.astrocolony.lodistics.spring.repository.MissaoRepository;
import com.fiap.astrocolony.lodistics.spring.repository.TripulanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MissaoService {

    private final MissaoRepository repository;
    private final TripulanteRepository tripulanteRepository;
    private final DiretorVooRepository diretorVooRepository;

    public Page<MissaoDto> listar(Pageable pagable) {
        Page<Missao> missaos = repository.findAll(pagable);
        return missaos.map(MissaoMapper::toDto);
    }

    @Transactional
    public MissaoDto salvar(MissaoRequestDto dto) {

        if (dto.idsTripulantes().size() > 5) {
            throw new RegraNegocioException(
                    "A missão não pode ter mais de 5 tripulantes!"
            );
        }

        List<Tripulante> tripulantesDaMissao = new ArrayList<>();
        for (Long idTripulante : dto.idsTripulantes()) {

            Tripulante tripulante = tripulanteRepository
                    .findById(idTripulante)
                    .orElseThrow(() ->
                            new EntidadeNaoLocalizadaException(
                                    "Tripulante não encontrado: " + idTripulante
                            )
                    );

            if (tripulante.getStatusTripulante() != StatusTripulante.DISPONIVEL) {
                throw new RegraNegocioException(
                        "O tripulante "
                                + tripulante.getNmTripulante()
                                + " não está disponível para a missão!"
                );
            }

            tripulante.setStatusTripulante(StatusTripulante.EM_MISSAO);
            tripulantesDaMissao.add(tripulante);
        }

        DiretorVoo diretorVoo = diretorVooRepository
                .findById(dto.idDiretorVoo())
                .orElseThrow(() ->
                        new EntidadeNaoLocalizadaException(
                                "Diretor de voo não encontrado!"
                        )
                );

        diretorVoo.setStatusDiretor(StatusTripulante.EM_MISSAO);
        Missao missao = Missao.builder()
                .nmMissao(dto.nmMissao())
                .destino(dto.destino())
                .qtdDias(dto.qtdDias())
                .qtdAlimento(dto.qtdAlimento())
                .qtdTripulantes(tripulantesDaMissao.size())
                .diretorVoo(diretorVoo)
                .tripulantes(tripulantesDaMissao)
                .statusMissao(dto.statusMissao())
                .build();
        Missao missaoSalva = repository.save(missao);
        return MissaoMapper.toDto(missaoSalva);
    }

    public MissaoDto buscarPorId(Long id) {
        Missao missao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missão não encontrada"));
        return MissaoMapper.toDto(missao);
    }

    public Page<MissaoDto> buscarMissaoPorStatus(String status, Pageable pageable) {
        StatusMissao statusMissao = StatusMissao.valueOf(status.toUpperCase());
        Page<Missao> missoes = repository.findAllByStatusMissao(statusMissao, pageable);
        return missoes.map(MissaoMapper::toDto);
    }

    public String iniciarMissao(Long id){
        Optional<Missao> missao = repository.findById(id);
        if (missao.isEmpty()) throw new EntidadeNaoLocalizadaException("Missão não encontrada!");
        Missao missaoEncontrada = missao.get();
        missaoEncontrada.setStatusMissao(StatusMissao.OCORRENDO);
        repository.save(missaoEncontrada);
        return "Missão iniciada com sucesso!";
    }

    public void deletar(Long id) {

        repository.deleteById(id);}
}
