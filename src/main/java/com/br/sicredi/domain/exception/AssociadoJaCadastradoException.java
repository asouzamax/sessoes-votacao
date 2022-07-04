package com.br.sicredi.domain.exception;

import org.springframework.http.HttpStatus;

public class AssociadoJaCadastradoException extends NegocioException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AssociadoJaCadastradoException(String nome) {
        super(HttpStatus.BAD_REQUEST, "Associado já está cadastrado: " + nome);
    }

}
