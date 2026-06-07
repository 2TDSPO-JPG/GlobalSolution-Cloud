package com.fiap.astrocolony.lodistics.spring.controller;

import com.fiap.astrocolony.lodistics.spring.dto.DiretorVooDto;
import com.fiap.astrocolony.lodistics.spring.dto.function.LoginDto;
import com.fiap.astrocolony.lodistics.spring.entity.DiretorVoo;
import com.fiap.astrocolony.lodistics.spring.service.DiretorVooService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diretor-voo")
@RequiredArgsConstructor
public class DiretorVooController {

    private final DiretorVooService diretorVooService;

    @PostMapping
    public DiretorVooDto salvar(@RequestBody DiretorVoo diretorVoo){
        return diretorVooService.salvar(diretorVoo);
    }

    @PostMapping("/login")
    public DiretorVooDto login(@RequestBody LoginDto login){
        return diretorVooService.login(login);
    }

    @PutMapping("/alterar-status/{id}-{status}")
    public DiretorVooDto alterarStatus(@PathVariable("id") Long idDiretor, @PathVariable("status") String status) {
        return diretorVooService.alterarStatus(idDiretor,status);
    }

    @GetMapping
    public Page<DiretorVooDto> listarTodos( Pageable pageable) {
        return diretorVooService.listarTodos(pageable);
    }

    @GetMapping("/buscar-por-")
    public DiretorVooDto buscarPorId(@RequestParam Long id) {
        return diretorVooService.buscarPorId(id);
    }

}
