package com.fiap.astrocolony.lodistics.spring.controller;

import com.fiap.astrocolony.lodistics.spring.dto.TripulanteDto;
import com.fiap.astrocolony.lodistics.spring.dto.function.LoginDto;
import com.fiap.astrocolony.lodistics.spring.dto.requests.TripulanteAtualizar;
import com.fiap.astrocolony.lodistics.spring.dto.requests.TripulanteRequestDto;
import com.fiap.astrocolony.lodistics.spring.service.TripulanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tripulantes")
public class TripulanteController {

    private final TripulanteService service;

    @PostMapping
    public TripulanteDto criarTripulante(@RequestBody TripulanteRequestDto tripulanteRequestDto){
        return service.salvar(tripulanteRequestDto);
    }

    @GetMapping("/buscar-por-pin/{pin}")
    public TripulanteDto buscarPorPin(@PathVariable("pin") String pin) {
        return service.buscarPorPin(pin);
    }

    @PutMapping("/mudar-status")
    public TripulanteDto mudarStatusTripulante(@RequestBody TripulanteAtualizar tripulanteRequestDto){
        return service.mudarStatusTripulante(tripulanteRequestDto);
    }

    @DeleteMapping("/deletar-por-pin/{pin}")
    public void deletarPorPin(@PathVariable("pin") String pin) {
        service.deletarPorPin(pin);
    }

    @PostMapping("/login")
    public TripulanteDto login(@RequestBody LoginDto login){
        return service.login(login);
    }

    @GetMapping("/buscar-por-status/{status}")
    public Page<TripulanteDto> buscarPorStatus(@PathVariable("status") String status, Pageable pageable) {
        return service.buscarTripulantePorStatus(status, pageable);
    }

}
