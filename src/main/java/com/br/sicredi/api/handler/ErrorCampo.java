package com.br.sicredi.api.handler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorCampo {

  private final String nome;

  private final String descricao;

}
