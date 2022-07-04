package com.br.sicredi.service;

import com.br.sicredi.domain.associado.Associado;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AbstractServiceTest {

    LocalDateTime localDateTimeNow = LocalDateTime.now();
    private Associado associado;

    protected void setupAssociado() {
        associado = Associado.builder()
                .nome("Teste")
                .build();
        associado.setId("98765");

    }
}
