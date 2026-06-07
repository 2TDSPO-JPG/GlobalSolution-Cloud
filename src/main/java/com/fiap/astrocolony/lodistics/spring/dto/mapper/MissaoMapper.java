package com.fiap.astrocolony.lodistics.spring.dto.mapper;

import com.fiap.astrocolony.lodistics.spring.dto.MissaoDto;
import com.fiap.astrocolony.lodistics.spring.entity.Missao;

public class MissaoMapper {

    public static MissaoDto toDto(Missao missao){
        return MissaoDto.builder()
                .idMissao(missao.getIdMissao())
                .nmMissao(missao.getNmMissao())
                .destino(missao.getDestino())
                .qtdTripulantes(missao.getQtdTripulantes())
                .qtdDias(missao.getQtdDias())
                .qtdAlimento(missao.getQtdAlimento())
                .diretorVoo(missao.getDiretorVoo())
                .tripulantes(missao.getTripulantes())
                .statusMissao(missao.getStatusMissao())
                .build();
    }

}
