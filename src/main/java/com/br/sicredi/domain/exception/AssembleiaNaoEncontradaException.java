package com.br.sicredi.domain.exception;

import org.springframework.http.HttpStatus;

public class AssembleiaNaoEncontradaException extends NegocioException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AssembleiaNaoEncontradaException() {
        super(HttpStatus.NOT_FOUND, "Assembléia não encontrada");
    }

}
