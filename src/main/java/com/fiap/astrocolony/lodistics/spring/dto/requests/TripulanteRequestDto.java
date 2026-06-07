package com.fiap.astrocolony.lodistics.spring.dto.requests;

import com.fiap.astrocolony.lodistics.spring.entity.Missao;
import com.fiap.astrocolony.lodistics.spring.enuns.StatusTripulante;

public record TripulanteRequestDto(
        String nmTripulante,
        Double pesoTripulante,
        Double altura,
        String senha,
        StatusTripulante statusTripulante) {}
