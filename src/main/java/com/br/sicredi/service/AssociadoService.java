package com.br.sicredi.service;

import com.br.sicredi.domain.associado.Associado;
import com.br.sicredi.domain.associado.AssociadoRepository;
import com.br.sicredi.domain.exception.AssociadoJaCadastradoException;
import com.br.sicredi.domain.exception.AssociadoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.time.OffsetDateTime;

@Service
@RequestScope
@RequiredArgsConstructor
public class AssociadoService {

    private final AssociadoRepository repository;

    public Associado create(final Associado associado) {
        final var associadoEncontrado = repository.findByNome(associado.getNome());

        if (associadoEncontrado.isPresent()) {
            throw new AssociadoJaCadastradoException(associado.getNome());
        }

        associado.setId(null);
        associado.setCadastro(OffsetDateTime.now());

        return repository.save(associado);
    }

    public Associado update(Associado associado) {
        findById(associado.getId());
        return repository.save(associado);
    }

    public Associado findById(String id) {
        return repository.findById(id).orElseThrow(() -> new AssociadoNaoEncontradoException());
    }
}
