package com.br.sicredi.service;

import com.br.sicredi.domain.assembleia.Assembleia;
import com.br.sicredi.domain.assembleia.StatusAssembleiaEnum;
import com.br.sicredi.domain.associado.Associado;
import com.br.sicredi.domain.exception.AssembleiaNaoEncontradaException;
import com.br.sicredi.domain.exception.NegocioException;
import com.br.sicredi.domain.voto.TipoVotoEnum;
import com.br.sicredi.domain.voto.Voto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VotacaoServiceTest extends AbstractServiceTest {

    @Mock
    private AssembleiaService mockAssembleiaService;
    @Mock
    private AssociadoService mockAssociadoService;

    private VotacaoService votacaoServiceUnderTest;

    @BeforeEach
    void setUp() {
        votacaoServiceUnderTest = new VotacaoService(mockAssembleiaService, mockAssociadoService);
        setupAssociado();
    }

    @Test
    void testCreate() {
        final Voto expectedResult = new Voto(getAssociado(), TipoVotoEnum.SIM);

        final Assembleia assembleia = new Assembleia("pauta", new HashSet<>(), OffsetDateTime.of(getLocalDateTimeNow(), ZoneOffset.UTC), StatusAssembleiaEnum.ABERTA);
        assembleia.setId("001");
        when(mockAssembleiaService.findAssembleia(any())).thenReturn(assembleia);
        when(mockAssociadoService.findById(any())).thenReturn(getAssociado());
        when(mockAssembleiaService.update(any(Assembleia.class))).thenReturn(assembleia);

        // Run the test
        final Voto result = votacaoServiceUnderTest.create(assembleia.getId(), TipoVotoEnum.SIM.name(), getAssociado().getId());

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockAssembleiaService).update(any(Assembleia.class));
    }

    @Test
    void testCreateThrowsExceptionVotoJaComputado() {
        final Voto expectedResult = new Voto(getAssociado(), TipoVotoEnum.SIM);

        final Assembleia assembleia = new Assembleia("pauta", Set.of(new Voto(getAssociado(), TipoVotoEnum.SIM)), OffsetDateTime.of(getLocalDateTimeNow(), ZoneOffset.UTC), StatusAssembleiaEnum.ABERTA);
        assembleia.setId("001");
        when(mockAssembleiaService.findAssembleia(any())).thenReturn(assembleia);
        when(mockAssociadoService.findById(any())).thenReturn(getAssociado());

        Throwable exception = assertThrows(NegocioException.class, () -> {
            votacaoServiceUnderTest.create(assembleia.getId(), TipoVotoEnum.SIM.name(), getAssociado().getId());
        });
        assertEquals("O voto ja foi computado!", exception.getMessage());
    }

    @Test
    void testCreateThrowsExceptionVotacaoEncerrada() {
        final Voto expectedResult = new Voto(getAssociado(), TipoVotoEnum.SIM);

        final Assembleia assembleia = new Assembleia("pauta", new HashSet<>(), OffsetDateTime.of(getLocalDateTimeNow(), ZoneOffset.UTC), StatusAssembleiaEnum.ENCERRADA);
        assembleia.setId("001");
        when(mockAssembleiaService.findAssembleia(any())).thenReturn(assembleia);
        when(mockAssociadoService.findById(any())).thenReturn(getAssociado());

        Throwable exception = assertThrows(NegocioException.class, () -> {
            votacaoServiceUnderTest.create(assembleia.getId(), TipoVotoEnum.SIM.name(), getAssociado().getId());
        });
        assertEquals("A pauta de votaçao náo esta aberta!", exception.getMessage());
    }

    //
}
