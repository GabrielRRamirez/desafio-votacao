package com.dbserver.desafiovotacao.modules.pauta.usecase.impl;

import com.dbserver.desafiovotacao.modules.pauta.domain.dto.CreatePautaDTO;
import com.dbserver.desafiovotacao.modules.pauta.domain.dto.DetailPautaDTO;
import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;
import com.dbserver.desafiovotacao.modules.pauta.repository.PautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CreatePautaUseCaseImplTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private CreatePautaUseCaseImpl createPautaUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        CreatePautaDTO createPauta = new CreatePautaDTO("PAUTA TESTE");
        Pauta pauta = new Pauta(createPauta);

        when(pautaRepository.save(pauta)).thenReturn(pauta);

        assertEquals(new DetailPautaDTO(pauta), createPautaUseCase.create(createPauta));
    }
}