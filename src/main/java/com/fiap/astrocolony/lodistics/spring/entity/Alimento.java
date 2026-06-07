package com.fiap.astrocolony.lodistics.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "T_ALIMENTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlimento;

    private String nmAlimento;

    private Double pesoAlimento;

    private Integer kcal;
    @ManyToMany(mappedBy = "alimentos")
    @JsonIgnore
    private List<Missao> missao;

}