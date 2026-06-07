package com.fiap.astrocolony.lodistics.spring.dto.mapper;

import com.fiap.astrocolony.lodistics.spring.dto.TripulanteDto;
import com.fiap.astrocolony.lodistics.spring.entity.Tripulante;

public class TripulanteMapper {

    public static TripulanteDto toDto(Tripulante tripulante){
        return TripulanteDto.builder()
                .idTripulante(tripulante.getIdTripulante())
                .nmTripulante(tripulante.getNmTripulante())
                .pesoTripulante(tripulante.getPesoTripulante())
                .altura(tripulante.getAltura())
                .pin(tripulante.getPin())
                .consumoMedio(tripulante.getConsumoMedio())
                .consumoDiario(tripulante.getConsumoDiario())
                .missao(tripulante.getMissao())
                .statusTripulante(tripulante.getStatusTripulante())
                .build();
    }

}
