package com.fiap.astrocolony.lodistics.spring.dto;

import com.fiap.astrocolony.lodistics.spring.entity.Missao;
import com.fiap.astrocolony.lodistics.spring.enuns.StatusTripulante;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripulanteDto {
    private Long idTripulante;
    private String nmTripulante;
    private Double pesoTripulante;
    private Double altura;
    private String pin;
    private Double consumoMedio;
    private Double consumoDiario;
    private Missao missao;
    private StatusTripulante statusTripulante;
}
