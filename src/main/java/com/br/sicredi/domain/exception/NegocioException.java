package com.br.sicredi.domain.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

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
