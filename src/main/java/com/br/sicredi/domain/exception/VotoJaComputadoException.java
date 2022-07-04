package com.br.sicredi.domain.exception;

import org.springframework.http.HttpStatus;

public class VotoJaComputadoException extends NegocioException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public VotoJaComputadoException() {
        super(HttpStatus.BAD_REQUEST, "O voto ja foi computado!");
    }

}
