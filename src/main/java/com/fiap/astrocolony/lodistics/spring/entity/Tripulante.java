package com.fiap.astrocolony.lodistics.spring.entity;

import com.fiap.astrocolony.lodistics.spring.enuns.StatusTripulante;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "T_TRIPULANTE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tripulante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTripulante;

    private String nmTripulante;

    private Double pesoTripulante;

    private Double altura;

    private String pin;

    private Double consumoMedio;

    private Double consumoDiario;

    @ManyToOne
    @JoinColumn(name = "id_missao")
    private Missao missao;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status do tripulante não pode ser vazio!")
    private StatusTripulante statusTripulante;
    @NotBlank(message = "A senha do tripulante não pode ser vazia!")
    private String senha;

    @PrePersist
    @PreUpdate
    public void prepararDados() {

        if (this.pin == null || this.pin.isBlank()) {
            gerarPin();
        }

        calcularConsumo();
    }

    public void gerarPin() {
        String pin =  UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 8).toUpperCase();
        while (pin.length() < 8){
            pin += UUID.randomUUID().toString().replaceAll("[^0-9]", "");
        }
        this.pin = "TRIP-" + pin.substring(0, 8);
    }

    public void calcularConsumo() {

        this.consumoDiario = (this.pesoTripulante * 24)
                + (this.altura * 10);

        this.consumoMedio = this.consumoDiario / 24;
    }

}