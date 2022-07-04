package com.br.sicredi.service;

import com.br.sicredi.domain.assembleia.Assembleia;
import com.br.sicredi.domain.assembleia.AssembleiaRepository;
import com.br.sicredi.domain.assembleia.StatusAssembleiaEnum;
import com.br.sicredi.domain.exception.AssembleiaNaoEncontradaException;
import com.br.sicredi.domain.exception.NegocioException;
import com.br.sicredi.domain.voto.TipoVotoEnum;
import com.br.sicredi.domain.voto.Voto;
import org.junit.jupiter.api.Assertions;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AssembleiaServiceTest extends AbstractServiceTest {

    Assembleia assembleia;
    @Mock
    private AssembleiaRepository mockRepository;
    private AssembleiaService assembleiaServiceUnderTest;

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
        Assertions.assertEquals(result.getStatus(), StatusAssembleiaEnum.CRIADA);

    }

    @Test
    void testUpdate() {
        when(mockRepository.findById(any())).thenReturn(Optional.of(assembleia));
        when(mockRepository.save(any(Assembleia.class))).thenReturn(assembleia);

        final Assembleia result = assembleiaServiceUnderTest.update(assembleia);

        Assertions.assertNotNull(result.getId());
    }

    @Test
    void testOpenSession() {
        when(mockRepository.findById(any())).thenReturn(Optional.of(assembleia));
        when(mockRepository.save(any(Assembleia.class))).thenReturn(assembleia);

        final Assembleia result = assembleiaServiceUnderTest.openSession(assembleia.getId());

        Assertions.assertEquals(result.getStatus(), StatusAssembleiaEnum.ABERTA);
    }

    @Test
    void testOpenSessionAlredyOpened() {
        assembleia.setStatus(StatusAssembleiaEnum.ABERTA);
        when(mockRepository.findById(any())).thenReturn(Optional.of(assembleia));

        Throwable exception = assertThrows(NegocioException.class, () -> {
            assembleiaServiceUnderTest.openSession(assembleia.getId());
        });
        Assertions.assertEquals("Assembleia ja em votação", exception.getMessage());
    }

    @Test
    void testOpenSession_AssembleiaRepositoryFindByIdReturnsAbsent() {
        when(mockRepository.findById(any())).thenReturn(Optional.empty());
        Throwable exception = assertThrows(AssembleiaNaoEncontradaException.class, () -> {
            assembleiaServiceUnderTest.openSession(assembleia.getId());
        });
        Assertions.assertEquals("Assembléia não encontrada", exception.getMessage());

    }

    @Test
    void testFindAssembleia() {
        when(mockRepository.findById(any())).thenReturn(Optional.of(assembleia));

        final Assembleia result = assembleiaServiceUnderTest.findAssembleia(assembleia.getId());
        Assertions.assertNotNull(result);

    }

}
