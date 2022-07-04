package com.br.sicredi.service;

import com.br.sicredi.domain.assembleia.Assembleia;
import com.br.sicredi.domain.assembleia.AssembleiaRepository;
import com.br.sicredi.domain.assembleia.StatusAssembleiaEnum;
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

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AssembleiaServiceTest extends AbstractServiceTest {

    @Mock
    private AssembleiaRepository mockRepository;
    private AssembleiaService assembleiaServiceUnderTest;

    Assembleia assembleia;

    @BeforeEach
    void setUp() {
        assembleiaServiceUnderTest = new AssembleiaService(mockRepository);
        setupAssociado();
        assembleia = new Assembleia("pauta", Set.of(new Voto(getAssociado(), TipoVotoEnum.SIM)), OffsetDateTime.of(localDateTimeNow, ZoneOffset.UTC), StatusAssembleiaEnum.CRIADA);
        assembleia.setId("001");

    }

    @Test
    void testCreate() {
        when(mockRepository.save(any(Assembleia.class))).thenReturn(assembleia);
        final Assembleia result = assembleiaServiceUnderTest.create(assembleia);
        assertThat(result.getStatus()).isEqualTo(StatusAssembleiaEnum.CRIADA);

    }

    @Test
    void testUpdate() {
        when(mockRepository.findById(any())).thenReturn( Optional.of(assembleia));
        when(mockRepository.save(any(Assembleia.class))).thenReturn(assembleia);

        final Assembleia result = assembleiaServiceUnderTest.update(assembleia);

        assertThat(result.getId()).isNotNull();
    }

    @Test
    void testOpenSession() {
        when(mockRepository.findById(any())).thenReturn( Optional.of(assembleia));
        when(mockRepository.save(any(Assembleia.class))).thenReturn(assembleia);

        final Assembleia result = assembleiaServiceUnderTest.openSession(assembleia.getId());

        assertThat(result.getStatus()).isEqualTo(StatusAssembleiaEnum.ABERTA);
    }

    @Test
    void testOpenSessionAlredyOpened() {
        assembleia.setStatus(StatusAssembleiaEnum.ABERTA);
        when(mockRepository.findById(any())).thenReturn( Optional.of(assembleia));

        Throwable exception = assertThrows(NegocioException.class, () -> {
            assembleiaServiceUnderTest.openSession(assembleia.getId());
        });
        assertEquals("Assembleia ja em votação", exception.getMessage());
    }

    @Test
    void testOpenSession_AssembleiaRepositoryFindByIdReturnsAbsent() {
        when(mockRepository.findById(any())).thenReturn(Optional.empty());
        Throwable exception = assertThrows(AssembleiaNaoEncontradaException.class, () -> {
            assembleiaServiceUnderTest.openSession(assembleia.getId());
        });
        assertEquals("Assembléia não encontrada", exception.getMessage());

    }

    @Test
    void testFindAssembleia() {
        when(mockRepository.findById(any())).thenReturn(Optional.of(assembleia));

        final Assembleia result = assembleiaServiceUnderTest.findAssembleia(assembleia.getId());
        assertThat(result).isNotNull();

    }

}
