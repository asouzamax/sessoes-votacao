package com.br.sicredi.domain.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NegocioException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private final HttpStatus status;

  public NegocioException(final HttpStatus status, final String message) {
    super(message);
    this.status = status;
  }

  public int cogetCode() {
    return status.value();
  }

}
