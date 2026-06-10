package com.fiap.astrocolony.lodistics.spring.controller;

import com.fiap.astrocolony.lodistics.spring.dto.requests.HistoricoConsulmoRequest;
import com.fiap.astrocolony.lodistics.spring.entity.HistoricoConsumo;
import com.fiap.astrocolony.lodistics.spring.service.HistoricoConsumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/historico-consumo")
@RequiredArgsConstructor
public class HistoricoConsumoController {

    private final HistoricoConsumoService historicoConsumoService;

    @PostMapping
    public HistoricoConsumo salvar(@RequestBody HistoricoConsulmoRequest historicoConsumo) {
        return historicoConsumoService.salvar(historicoConsumo);
    }

    @GetMapping
    public Page<HistoricoConsumo> listarTodas(Pageable pageable) {
        return historicoConsumoService.listarHistoricoConsumo(pageable);
    }

}
