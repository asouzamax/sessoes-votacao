package com.br.sicredi.service;

import com.br.sicredi.domain.associado.Associado;
import com.br.sicredi.domain.associado.AssociadoRepository;
import com.br.sicredi.domain.exception.AssociadoJaCadastradoException;
import com.br.sicredi.domain.exception.AssociadoNaoEncontradoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AssociadoServiceTest extends AbstractServiceTest {

    @Mock
    private AssociadoRepository mockRepository;

    private AssociadoService associadoServiceUnderTest;

    @BeforeEach
    void setUp() {

        associadoServiceUnderTest = new AssociadoService(mockRepository);
        setupAssociado();
    }

    @Test
    void testCreate() {
        when(mockRepository.save(any(Associado.class))).thenReturn(getAssociado());

        final Associado result = associadoServiceUnderTest.create(getAssociado());
        Assertions.assertEquals(result.getNome(), getAssociado().getNome());

    }

    @Test
    void testCreateAndThrowException() {
        when(mockRepository.findByNome(any())).thenReturn(Optional.of(getAssociado()));
        Throwable exception = assertThrows(AssociadoJaCadastradoException.class, () -> {
            associadoServiceUnderTest.create(getAssociado());
        });
        Assertions.assertEquals("Associado já está cadastrado: " + getAssociado().getNome(), exception.getMessage());

    }

    @Test
    void testUpdate() {
        Associado associado = getAssociado();
        associado.setNome("teste2");
        when(mockRepository.findById(any())).thenReturn(Optional.of(associado));
        when(mockRepository.save(any(Associado.class))).thenReturn(associado);

        // Run the test
        final Associado result = associadoServiceUnderTest.update(associado);

        Assertions.assertEquals(result.getNome(), associado.getNome());
    }

    @Test
    void testUpdate_AssociadoRepositoryFindByIdReturnsAbsent() {
        when(mockRepository.findById(any())).thenReturn(Optional.empty());

        Throwable exception = assertThrows(AssociadoNaoEncontradoException.class, () -> {
            associadoServiceUnderTest.update(getAssociado());
        });
        Assertions.assertEquals("Associado não encontrado", exception.getMessage());

    }

    @Test
    void testFindById() {
        when(mockRepository.findById(any())).thenReturn(Optional.of(getAssociado()));

        final Associado result = associadoServiceUnderTest.findById(getAssociado().getId());

        Assertions.assertNotNull(result.getId());
    }

}
