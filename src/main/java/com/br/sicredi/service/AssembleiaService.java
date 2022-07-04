package com.br.sicredi.service;

import com.br.sicredi.domain.assembleia.Assembleia;
import com.br.sicredi.domain.assembleia.AssembleiaRepository;
import com.br.sicredi.domain.assembleia.StatusAssembleiaEnum;
import com.br.sicredi.domain.exception.AssembleiaNaoEncontradaException;
import com.br.sicredi.domain.exception.NegocioException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class AssembleiaService {

    private AssembleiaRepository repository;


    public Assembleia create(final Assembleia assembleia) {

        assembleia.setStatus(StatusAssembleiaEnum.CRIADA);
        assembleia.setId(null);
        assembleia.setCadastro(OffsetDateTime.now());

        return repository.save(assembleia);
    }

    public Assembleia update(final Assembleia assembleia) {
        findAssembleia(assembleia.getId());
        return repository.save(assembleia);
    }

    public Assembleia openSession(final String assembleiaId) {

        final Assembleia assembleiaEncontrada = findAssembleia(assembleiaId);
        if (assembleiaEncontrada.getStatus().equals(StatusAssembleiaEnum.ABERTA)) {
            throw new NegocioException(HttpStatus.BAD_REQUEST, "Assembleia ja em votação");
        }
        assembleiaEncontrada.setStatus(StatusAssembleiaEnum.ABERTA);
        assembleiaEncontrada.setAbertura(OffsetDateTime.now());

        return repository.save(assembleiaEncontrada);
    }

    public Assembleia findAssembleia(String assembleiaId) {
        return repository.findById(assembleiaId)
                .orElseThrow(() -> new AssembleiaNaoEncontradaException());
    }

}
