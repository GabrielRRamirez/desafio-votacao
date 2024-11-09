package com.dbserver.desafiovotacao.modules.sessao.application.impl;

import com.dbserver.desafiovotacao.modules.sessao.domain.entity.Sessao;
import com.dbserver.desafiovotacao.modules.sessao.usecase.FindSessaoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class SessaoApplicationImplTest {

    @Mock
    private FindSessaoUseCase findSessaoUseCase;

    @InjectMocks
    private SessaoApplicationImpl sessaoApplication;

    @BeforeEach
    void setUp() {
        Locale.setDefault(new Locale("pt", "BR"));
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("America/Sao_Paulo")));
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHasPautaSessaoAbertaWhenNotFoundSessao() {
        assertFalse(sessaoApplication.hasPautaSessaoAberta(1L));
    }

    @ParameterizedTest
    @MethodSource("getSessaoPeriodoInvalido")
    void testHasPautaSessaoAbertaWhenPeriodIsInvalid(Sessao sessao) {
        when(findSessaoUseCase.findByPautaId(1L)).thenReturn(sessao);
        assertFalse(sessaoApplication.hasPautaSessaoAberta(1L));
    }

    @Test
    void testHasPautaSessaoAbertaWhenPeriodIsValid() {
        Sessao sessao = new Sessao();
        sessao.setDataHoraInicio(LocalDateTime.now().minusHours(1L));
        sessao.setDataHoraTermino(LocalDateTime.now().plusHours(1L));

        when(findSessaoUseCase.findByPautaId(1L)).thenReturn(sessao);

        assertTrue(sessaoApplication.hasPautaSessaoAberta(1L));
    }

    private static Stream<Arguments> getSessaoPeriodoInvalido() {

        Sessao sessaoDataInicioAntesPeriodo = new Sessao();
        sessaoDataInicioAntesPeriodo.setDataHoraInicio(LocalDateTime.now().plusHours(1L));
        sessaoDataInicioAntesPeriodo.setDataHoraTermino(LocalDateTime.now().plusHours(2L));

        Sessao sessaoDataTerminoDepoisPeriodo = new Sessao();
        sessaoDataTerminoDepoisPeriodo.setDataHoraInicio(LocalDateTime.now().minusHours(2));
        sessaoDataTerminoDepoisPeriodo.setDataHoraTermino(LocalDateTime.now().minusHours(1));

        return Stream.of(
                Arguments.of(sessaoDataInicioAntesPeriodo),
                Arguments.of(sessaoDataTerminoDepoisPeriodo)
        );
    }
}