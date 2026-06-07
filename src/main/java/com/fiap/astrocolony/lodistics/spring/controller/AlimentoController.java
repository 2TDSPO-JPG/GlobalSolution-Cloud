package com.fiap.astrocolony.lodistics.spring.controller;

import com.fiap.astrocolony.lodistics.spring.dto.AlimentosDto;
import com.fiap.astrocolony.lodistics.spring.dto.requests.AlimentoRequestDto;
import com.fiap.astrocolony.lodistics.spring.entity.Alimento;
import com.fiap.astrocolony.lodistics.spring.service.AlimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alimentos")
public class AlimentoController {

    private final AlimentoService alimentoService;

    @GetMapping
    public Page<AlimentosDto> listarTodos(Pageable pageable){
        return alimentoService.buscarTodos(pageable);
    }

    @PostMapping
    public AlimentosDto criarAlimento(@RequestBody AlimentoRequestDto alimentos){
        return alimentoService.salvar(alimentos);
    }

    @GetMapping("/buscar-por-")
    public AlimentosDto buscarPorId(@RequestParam Long id){
        return alimentoService.buscarPorId(id);
    }

    @PutMapping
    public AlimentosDto atualizarAlimento(Alimento alimento){
        return alimentoService.atualizar(alimento);
    }

    @DeleteMapping("/deletar-")
    public String deletarAlimento(@RequestParam Long id) {
        return alimentoService.deletar(id);
    }

}
