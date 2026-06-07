package com.fiap.astrocolony.lodistics.spring.service;

import com.fiap.astrocolony.lodistics.spring.dto.DiretorVooDto;
import com.fiap.astrocolony.lodistics.spring.dto.function.LoginDto;
import com.fiap.astrocolony.lodistics.spring.dto.mapper.DiretorMapper;
import com.fiap.astrocolony.lodistics.spring.entity.DiretorVoo;
import com.fiap.astrocolony.lodistics.spring.enuns.StatusTripulante;
import com.fiap.astrocolony.lodistics.spring.exception.exceptions.EntidadeNaoLocalizadaException;
import com.fiap.astrocolony.lodistics.spring.exception.exceptions.RegraNegocioException;
import com.fiap.astrocolony.lodistics.spring.repository.DiretorVooRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiretorVooService {

    private final DiretorVooRepository diretorVooRepository;

    public DiretorVooDto salvar(DiretorVoo diretorVoo) {
        diretorVoo.setStatusDiretor(StatusTripulante.DISPONIVEL);
        DiretorVoo diretorSalvo = diretorVooRepository.save(diretorVoo);
        return DiretorMapper.toDto(diretorSalvo);
    }

    public DiretorVooDto login(LoginDto login){
        String emailDiretor = login.getEmailOrPin();
        String senhaDiretor = login.getSenha();
        if (emailDiretor.isEmpty() || senhaDiretor.isEmpty()) {
            throw new RegraNegocioException("Email e senha devem ser fornecidos.");
        }
        Optional<DiretorVoo> diretorVoo = diretorVooRepository.findByEmailDiretorAndSenhaDiretor(emailDiretor, senhaDiretor);
        DiretorVoo diretorVoo1 = diretorVoo.orElseThrow(() -> new EntidadeNaoLocalizadaException("Diretor de voo não encontrado com as credenciais fornecidas."));
        return DiretorMapper.toDto(diretorVoo1);
    }

    public DiretorVooDto alterarStatus(Long idDiretor, String status) {
        DiretorVoo diretorVoo = diretorVooRepository.findById(idDiretor)
                .orElseThrow(() -> new EntidadeNaoLocalizadaException("Diretor de voo não encontrado com o ID: " + idDiretor));
        if (StatusTripulante.valueOf(status.toUpperCase()) == StatusTripulante.DISPONIVEL){
            throw new RegraNegocioException("O status do diretor de voo não pode ser alterado para DISPONIVEL.");
        }
        diretorVoo.setStatusDiretor(StatusTripulante.valueOf(status.toUpperCase()));
        DiretorVoo diretorAtualizado = diretorVooRepository.save(diretorVoo);
        return DiretorMapper.toDto(diretorAtualizado);
    }

    public Page<DiretorVooDto> listarTodos(Pageable pageable) {
         Page<DiretorVoo> diretorVoos = diretorVooRepository.findAll(pageable);
         return diretorVoos.map(DiretorMapper::toDto);
    }

    public DiretorVooDto buscarPorId(Long id) {
        DiretorVoo diretorVoo = diretorVooRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoLocalizadaException("Diretor de voo não encontrado com o ID: " + id));
        return DiretorMapper.toDto(diretorVoo);
    }

}
