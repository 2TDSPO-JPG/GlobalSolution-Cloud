package com.fiap.astrocolony.lodistics.spring.dto.mapper;

import com.fiap.astrocolony.lodistics.spring.dto.DiretorVooDto;
import com.fiap.astrocolony.lodistics.spring.entity.DiretorVoo;

public class DiretorMapper {

    public static DiretorVooDto toDto(DiretorVoo diretorVoo){
        return DiretorVooDto.builder()
                .idDiretorVoo(diretorVoo.getIdDiretorVoo())
                .nmDiretor(diretorVoo.getNmDiretor())
                .emailDiretor(diretorVoo.getEmailDiretor())
                .missao(diretorVoo.getMissao())
                .statusDiretor(diretorVoo.getStatusDiretor())
                .pin(diretorVoo.getPin())
                .build();
    }

}
