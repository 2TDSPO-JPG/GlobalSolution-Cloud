package com.fiap.astrocolony.lodistics.spring.dto;

import com.fiap.astrocolony.lodistics.spring.entity.Missao;
import com.fiap.astrocolony.lodistics.spring.enuns.StatusTripulante;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiretorVooDto {

    private Long idDiretorVoo;
    private String nmDiretor;
    private String emailDiretor;
    private String senhaDiretor;
    private Missao missao;
    private StatusTripulante statusDiretor;
    private String pin;

}
