package com.br.sicredi.domain.exception;

import org.springframework.http.HttpStatus;

public class VotoJaComputadoException extends NegocioException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public VotoJaComputadoException() {
    super(HttpStatus.NOT_FOUND, "O voto ja foi computado!");
  }

}
