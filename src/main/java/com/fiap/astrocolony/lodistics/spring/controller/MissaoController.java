package com.fiap.astrocolony.lodistics.spring.controller;

import com.fiap.astrocolony.lodistics.spring.dto.MissaoDto;
import com.fiap.astrocolony.lodistics.spring.dto.requests.MissaoRequestDto;
import com.fiap.astrocolony.lodistics.spring.service.MissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
@RequiredArgsConstructor
public class MissaoController {

    private final MissaoService service;

    @GetMapping
    public Page<MissaoDto> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @PostMapping
    public MissaoDto salvar(@RequestBody MissaoRequestDto missaoRequestDto) {
        return service.salvar(missaoRequestDto);
    }

    @GetMapping("/{id}")
    public MissaoDto buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/status/{status}")
    public Page<MissaoDto> buscarMissaoPorStatus(@PathVariable("status") String status, Pageable pageable) {
        return service.buscarMissaoPorStatus(status, pageable);
    }

    @PutMapping("/iniciar-missao/{id}")
    public String iniciarMissao(@PathVariable("id") Long id) {
        return service.iniciarMissao(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") Long id) {
        service.deletar(id);
    }



}
