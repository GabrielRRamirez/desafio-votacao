package com.dbserver.desafiovotacao.modules.associado.application.impl;

import com.dbserver.desafiovotacao.modules.associado.domain.entity.Associado;
import com.dbserver.desafiovotacao.modules.associado.usecase.FindAssociadoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AssociadoApplicationImplTest {

    @Mock
    private FindAssociadoUseCase findAssociadoUseCase;

    @InjectMocks
    private AssociadoApplicationImpl associadoApplication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        when(findAssociadoUseCase.findEntityById(1L)).thenReturn(new Associado());
        assertEquals(new Associado(), associadoApplication.findById(1L));
    }
}