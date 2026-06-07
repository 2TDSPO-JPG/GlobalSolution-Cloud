package com.fiap.astrocolony.lodistics.spring.dto.requests;

import com.fiap.astrocolony.lodistics.spring.entity.Missao;

import java.util.List;

public record AlimentoRequestDto(
        Long idAlimento,
        String nmAlimento,
        Double pesoAlimento,
        Integer kcal,
        List<Long> missao) {}
