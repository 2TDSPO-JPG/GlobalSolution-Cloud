package com.fiap.astrocolony.lodistics.spring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_ESTOQUE_MISSAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstoqueMissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double qtdInicial;

    private Double qtdAtual;

    @ManyToOne
    @JoinColumn(name = "id_missao")
    private Missao missao;

    @ManyToOne
    @JoinColumn(name = "id_alimento")
    private Alimento alimento;
}
