package com.fiap.astrocolony.lodistics.spring.dto;

import com.fiap.astrocolony.lodistics.spring.entity.Missao;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlimentosDto {

    private Long idAlimento;
    private String nmAlimento;
    private Double pesoAlimento;
    private Integer kcal;
    private List<Missao> missao;

}
