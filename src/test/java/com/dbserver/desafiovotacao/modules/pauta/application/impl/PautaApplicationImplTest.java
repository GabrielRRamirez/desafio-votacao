package com.dbserver.desafiovotacao.modules.pauta.application.impl;

import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;
import com.dbserver.desafiovotacao.modules.pauta.usecase.FindPautaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PautaApplicationImplTest {

    @Mock
    private FindPautaUseCase findPautaUseCase;

    @InjectMocks
    private PautaApplicationImpl pautaApplication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Pauta expected = new Pauta();
        when(findPautaUseCase.findEntityById(1L)).thenReturn(expected);
        assertEquals(expected, pautaApplication.findById(1L));
    }
}