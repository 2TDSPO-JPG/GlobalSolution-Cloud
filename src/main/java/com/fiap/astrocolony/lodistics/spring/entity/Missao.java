package com.fiap.astrocolony.lodistics.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.astrocolony.lodistics.spring.enuns.StatusMissao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "T_MISSAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Missao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMissao;

    private String nmMissao;

    private String destino;

    private Integer qtdTripulantes;

    private Integer qtdDias;

    private Double qtdAlimento;

    @OneToOne
    @JoinColumn(name = "id_diretorVoo")
    @NotNull(message = "A missão deve ter um diretor de voo associado!")
    @JsonIgnore
    private DiretorVoo diretorVoo;

    @OneToMany
    @JoinColumn(name = "id_missao")
    @JsonIgnore
    private List<Tripulante> tripulantes;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status da missão não pode ser vazio!")
    private StatusMissao statusMissao;
    @ManyToMany
    @JoinTable(name = "T_MISSAO_ALIMENTO",
            joinColumns = @JoinColumn(name = "id_missao"),
            inverseJoinColumns = @JoinColumn(name = "id_alimento"))
    private List<Alimento> alimentos;

}