package com.dbserver.desafiovotacao.modules.associado.usecase.impl;

import com.dbserver.desafiovotacao.infra.exceptions.ValidationException;
import com.dbserver.desafiovotacao.modules.associado.domain.dto.CreateAssociadoDTO;
import com.dbserver.desafiovotacao.modules.associado.domain.dto.DetailAssociadoDTO;
import com.dbserver.desafiovotacao.modules.associado.domain.entity.Associado;
import com.dbserver.desafiovotacao.modules.associado.repository.AssociadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateAssociadoUseCaseImplTest {

    @Mock
    private AssociadoRepository associadoRepository;

    @InjectMocks
    private CreateAssociadoUseCaseImpl createAssociadoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateWhenCpfAlreadyExists() {
        CreateAssociadoDTO createAssociado = new CreateAssociadoDTO("TESTE", "12345678012");

        when(associadoRepository.existsByCpf(createAssociado.cpf())).thenReturn(Boolean.TRUE);

        Throwable t = assertThrows(ValidationException.class, () -> createAssociadoUseCase.create(createAssociado));

        String expectedMessage = "CPF já utilizado!";

        assertEquals(expectedMessage, t.getMessage());
        verify(associadoRepository, never()).save(any(Associado.class));
    }

    @Test
    void testCreateWhenCpfDoesNotExists() {
        CreateAssociadoDTO createAssociado = new CreateAssociadoDTO("TESTE", "12345678012");

        when(associadoRepository.existsByCpf(createAssociado.cpf())).thenReturn(Boolean.FALSE);

        DetailAssociadoDTO expected = new DetailAssociadoDTO(new Associado(createAssociado));

        assertEquals(expected, createAssociadoUseCase.create(createAssociado));
    }
}