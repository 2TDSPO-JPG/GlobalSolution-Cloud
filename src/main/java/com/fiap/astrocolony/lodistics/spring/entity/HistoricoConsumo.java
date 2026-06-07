package com.fiap.astrocolony.lodistics.spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_HISTORICO_CONSUMO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricoConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsumo;

    private Double qtdConsumida;

    private LocalDateTime dataHoraConsumo;

    @ManyToOne
    @JoinColumn(name = "id_tripulante")
    private Tripulante tripulante;

    @ManyToOne
    @JoinColumn(name = "id_alimento")
    private Alimento alimento;
}