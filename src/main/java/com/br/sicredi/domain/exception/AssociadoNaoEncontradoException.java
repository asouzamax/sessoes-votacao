package com.br.sicredi.domain.exception;

import org.springframework.http.HttpStatus;

public class AssociadoNaoEncontradoException extends NegocioException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AssociadoNaoEncontradoException() {
        super(HttpStatus.NOT_FOUND, "Associado não encontrado");
    }

}
