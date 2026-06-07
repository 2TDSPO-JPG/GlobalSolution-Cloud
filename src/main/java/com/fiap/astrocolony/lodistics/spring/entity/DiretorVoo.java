package com.fiap.astrocolony.lodistics.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.astrocolony.lodistics.spring.enuns.StatusTripulante;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "T_DIRETORVOO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiretorVoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDiretorVoo;
    @NotBlank(message = "O nome do diretor de voo não pode ser vazio")
    private String nmDiretor;
    @NotBlank(message = "O email do diretor de voo não pode ser vazio")
    private String emailDiretor;
    @NotBlank(message = "A senha do diretor de voo não pode ser vazia")
    private String senhaDiretor;

    @OneToOne
    @JoinColumn(name = "id_missao")
    @JsonIgnore
    private Missao missao;

    @Enumerated(EnumType.STRING)
    private StatusTripulante statusDiretor;

    private String pin;

    @PrePersist
    public void gerarPin() {
        String pin = "TRIP-" + UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 8).toUpperCase();
        while (pin.length() < 8){
            pin += UUID.randomUUID().toString().replaceAll("[^0-9]", "");
        }
        this.pin = pin.substring(0, 8);
    }

}