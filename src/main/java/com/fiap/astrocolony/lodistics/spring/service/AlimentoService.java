package com.fiap.astrocolony.lodistics.spring.service;

import com.fiap.astrocolony.lodistics.spring.dto.AlimentosDto;
import com.fiap.astrocolony.lodistics.spring.dto.mapper.AlimentoMapper;
import com.fiap.astrocolony.lodistics.spring.dto.requests.AlimentoRequestDto;
import com.fiap.astrocolony.lodistics.spring.entity.Alimento;
import com.fiap.astrocolony.lodistics.spring.entity.Missao;
import com.fiap.astrocolony.lodistics.spring.exception.exceptions.EntidadeNaoLocalizadaException;
import com.fiap.astrocolony.lodistics.spring.exception.exceptions.RegraNegocioException;
import com.fiap.astrocolony.lodistics.spring.repository.AlimentoRepository;
import com.fiap.astrocolony.lodistics.spring.repository.MissaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlimentoService {

    private final AlimentoRepository alimentoRepository;
    private final MissaoRepository missaoRepository;

    @Transactional
    public AlimentosDto salvar(AlimentoRequestDto dto) {
        Missao missao = missaoRepository.findById(dto.missao().getFirst())
                .orElseThrow(() ->
                        new RegraNegocioException("Missão não encontrada"));
        Alimento alimento = Alimento.builder()
                .nmAlimento(dto.nmAlimento())
                .pesoAlimento(dto.pesoAlimento())
                .kcal(dto.kcal())
                .build();
        alimentoRepository.save(alimento);
        if (missao.getAlimentos() == null) {
            missao.setAlimentos(new ArrayList<>());
        }
        missao.getAlimentos().add(alimento);
        missaoRepository.save(missao);
        return AlimentoMapper.toDto(alimento);
    }

    public AlimentosDto buscarPorId(Long id){
        Alimento alimento = alimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alimento não encontrado"));
        return AlimentoMapper.toDto(alimento);
    }

    public AlimentosDto atualizar(Alimento alimento){
        Alimento alimentoExistente = alimentoRepository.findById(alimento.getIdAlimento())
                .orElseThrow(() -> new RuntimeException("Alimento não encontrado"));
        alimentoExistente.setNmAlimento(alimento.getNmAlimento());
        alimentoExistente.setPesoAlimento(alimento.getPesoAlimento());
        alimentoExistente.setKcal(alimento.getKcal());
        Alimento alimentoAtualizado = alimentoRepository.save(alimentoExistente);
        return AlimentoMapper.toDto(alimentoAtualizado);
    }

    public String deletar(Long id){
        Alimento alimento = alimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alimento não encontrado"));
        alimentoRepository.delete(alimento);
        return "Alimento deletado com sucesso!";
    }

    public Page<AlimentosDto> buscarTodos(Pageable pageable){
        Page<Alimento> alimentos = alimentoRepository.findAll(pageable);
        if (alimentos.isEmpty()) throw new EntidadeNaoLocalizadaException("Nenhum alimento encontrado");
        return alimentos.map(AlimentoMapper::toDto);
    }

}
