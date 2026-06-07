package com.fiap.astrocolony.lodistics.spring.dto.mapper;

import com.fiap.astrocolony.lodistics.spring.dto.AlimentosDto;
import com.fiap.astrocolony.lodistics.spring.entity.Alimento;

public class AlimentoMapper {

    public static AlimentosDto toDto(Alimento alimento){
        return AlimentosDto.builder().idAlimento(alimento.getIdAlimento()).nmAlimento(alimento.getNmAlimento()).pesoAlimento(alimento.getPesoAlimento()).kcal(alimento.getKcal()).missao(alimento.getMissao()).build();
    }

}
