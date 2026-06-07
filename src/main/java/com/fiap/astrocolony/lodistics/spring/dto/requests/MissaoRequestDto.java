package com.fiap.astrocolony.lodistics.spring.dto.requests;

import com.fiap.astrocolony.lodistics.spring.enuns.StatusMissao;

import java.util.List;

public record MissaoRequestDto(
        String nmMissao,
        String destino,
        Integer qtdDias,
        Double qtdAlimento,
        Long idDiretorVoo,
        List<Long> idsTripulantes,
        StatusMissao statusMissao) {}
