package com.br.sicredi.service;

import com.br.sicredi.domain.assembleia.StatusAssembleiaEnum;
import com.br.sicredi.domain.exception.NegocioException;
import com.br.sicredi.domain.voto.TipoVotoEnum;
import com.br.sicredi.domain.voto.Voto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class VotacaoService {

    private AssembleiaService assembleiaService;
    private AssociadoService associadoService;

    public Voto create(final String assembleiaId, final String tipovoto, String associadoId) {
        final var assembleiaEncontrada = assembleiaService.findAssembleia(assembleiaId);
        final var associadoEncontrado = associadoService.findById(associadoId);

        if (!assembleiaEncontrada.getStatus().equals(StatusAssembleiaEnum.ABERTA)) {
            throw new NegocioException(HttpStatus.BAD_REQUEST, "A pauta de votaçao náo esta aberta!");
        }
        var voto = Voto.builder()
                .tipo(TipoVotoEnum.valueOf(tipovoto))
                .associado(associadoEncontrado)
                .build();
        voto.setId(null);
        voto.setCadastro(OffsetDateTime.now());

        assembleiaEncontrada.addVoto(voto);
        assembleiaService.update(assembleiaEncontrada);

        return voto;
    }
}
