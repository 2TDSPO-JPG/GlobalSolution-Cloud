package com.fiap.astrocolony.lodistics.spring.exception.handler;

import com.fiap.astrocolony.lodistics.spring.exception.exceptions.EntidadeNaoLocalizadaException;
import com.fiap.astrocolony.lodistics.spring.exception.exceptions.ErroNoCadastroException;
import com.fiap.astrocolony.lodistics.spring.exception.exceptions.ErrorAutenticacaoException;
import com.fiap.astrocolony.lodistics.spring.exception.exceptions.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<String> regraNegocioException(RegraNegocioException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(ErroNoCadastroException.class)
    public ResponseEntity<String> erroNoCadastro(ErroNoCadastroException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ErrorAutenticacaoException.class)
    public ResponseEntity<ErrorAutenticacaoException> errorAutenticacao(ErrorAutenticacaoException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex);
    }

    @ExceptionHandler(EntidadeNaoLocalizadaException.class)
    public ResponseEntity<EntidadeNaoLocalizadaException> entidadeNaoLocalizada(EntidadeNaoLocalizadaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
    }

}
