package com.dbserver.desafiovotacao.modules.sessao.usecase.impl;

import com.dbserver.desafiovotacao.infra.exceptions.ValidationException;
import com.dbserver.desafiovotacao.modules.pauta.application.PautaApplication;
import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;
import com.dbserver.desafiovotacao.modules.sessao.domain.dto.CreateSessaoDTO;
import com.dbserver.desafiovotacao.modules.sessao.domain.dto.DetailSessaoDTO;
import com.dbserver.desafiovotacao.modules.sessao.domain.entity.Sessao;
import com.dbserver.desafiovotacao.modules.sessao.repository.SessaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateSessaoUseCaseImplTest {

    @Mock
    private SessaoRepository sessaoRepository;

    @Mock
    private PautaApplication pautaApplication;

    @InjectMocks
    private CreateSessaoUseCaseImpl createSessaoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSessaoWhenAlreadyExistsSessaoToPauta() {

        CreateSessaoDTO createSessao = new CreateSessaoDTO(
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                1L);

        when(sessaoRepository.existsByPautaId(1L)).thenReturn(Boolean.TRUE);

        Throwable t = assertThrows(ValidationException.class, () -> createSessaoUseCase.createSessao(createSessao));

        String expectedMessage = "A pauta já possui uma sessão!";

        assertEquals(expectedMessage, t.getMessage());
    }

    @Test
    void testCreateSessaoWhenPeriodIsInvalid() {
        CreateSessaoDTO createSessao = new CreateSessaoDTO(
                LocalDateTime.now(),
                LocalDateTime.now().minusHours(1),
                1L);

        when(sessaoRepository.existsByPautaId(1L)).thenReturn(Boolean.FALSE);

        Throwable t = assertThrows(ValidationException.class, () -> createSessaoUseCase.createSessao(createSessao));

        String expectedMessage = "A data término não pode ser inferior à data início!";

        assertEquals(expectedMessage, t.getMessage());
    }

    @Test
    void testCreateSessaoWithoutDataTermino() {
        CreateSessaoDTO createSessao = new CreateSessaoDTO(
                LocalDateTime.now(),
                null,
                1L);

        when(sessaoRepository.existsByPautaId(1L)).thenReturn(Boolean.FALSE);
        when(pautaApplication.findById(1L)).thenReturn(new Pauta());

        Sessao expectedSessao = new Sessao(createSessao);
        expectedSessao.setPauta(new Pauta());
        expectedSessao.setDataHoraTermino(createSessao.dataHoraInicio().plusMinutes(1L));

        assertEquals(new DetailSessaoDTO(expectedSessao), createSessaoUseCase.createSessao(createSessao));

        verify(sessaoRepository).save(expectedSessao);
    }

    @Test
    void testCreateSessaoWhenHasDataTermino() {
        CreateSessaoDTO createSessao = new CreateSessaoDTO(
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                1L);

        when(sessaoRepository.existsByPautaId(1L)).thenReturn(Boolean.FALSE);
        when(pautaApplication.findById(1L)).thenReturn(new Pauta());

        Sessao expectedSessao = new Sessao(createSessao);
        expectedSessao.setPauta(new Pauta());

        assertEquals(new DetailSessaoDTO(expectedSessao), createSessaoUseCase.createSessao(createSessao));

        verify(sessaoRepository).save(expectedSessao);
    }
}