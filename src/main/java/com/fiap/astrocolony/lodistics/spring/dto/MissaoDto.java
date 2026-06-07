package com.fiap.astrocolony.lodistics.spring.dto;

import com.fiap.astrocolony.lodistics.spring.entity.DiretorVoo;
import com.fiap.astrocolony.lodistics.spring.entity.Tripulante;
import com.fiap.astrocolony.lodistics.spring.enuns.StatusMissao;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissaoDto {

    private Long idMissao;
    private String nmMissao;
    private String destino;
    private Integer qtdTripulantes;
    private Integer qtdDias;
    private Double qtdAlimento;
    private DiretorVoo diretorVoo;
    private List<Tripulante> tripulantes;
    private StatusMissao statusMissao;

}
