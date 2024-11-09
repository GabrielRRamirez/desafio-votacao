package com.dbserver.desafiovotacao.modules.voto.usecase.impl;

import com.dbserver.desafiovotacao.infra.exceptions.ValidationException;
import com.dbserver.desafiovotacao.modules.associado.application.AssociadoApplication;
import com.dbserver.desafiovotacao.modules.associado.domain.entity.Associado;
import com.dbserver.desafiovotacao.modules.pauta.application.PautaApplication;
import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;
import com.dbserver.desafiovotacao.modules.sessao.application.SessaoApplication;
import com.dbserver.desafiovotacao.modules.voto.domain.dto.CreateVotoDTO;
import com.dbserver.desafiovotacao.modules.voto.domain.dto.DetailVotoDTO;
import com.dbserver.desafiovotacao.modules.voto.domain.entity.Voto;
import com.dbserver.desafiovotacao.modules.voto.domain.enums.TipoEscolhaVoto;
import com.dbserver.desafiovotacao.modules.voto.repository.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CreateVotoUseCaseImplTest {

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private PautaApplication pautaApplication;

    @Mock
    private AssociadoApplication associadoApplication;

    @Mock
    private SessaoApplication sessaoApplication;

    @InjectMocks
    private CreateVotoUseCaseImpl createVotoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateVotoWhenVotoAlreadyExists() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);

        Associado associado = new Associado();
        associado.setId(2L);

        when(pautaApplication.findById(1L)).thenReturn(pauta);
        when(associadoApplication.findById(2L)).thenReturn(associado);
        when(votoRepository.existsByPautaIdAndAssociadoId(1L, 2L)).thenReturn(Boolean.TRUE);

        CreateVotoDTO createVoto = new CreateVotoDTO(2L, 1L, TipoEscolhaVoto.SIM.name());

        Throwable t = assertThrows(ValidationException.class, () -> createVotoUseCase.createVoto(createVoto));

        String expectedMessage = "Voto já computado para o associado na pauta!";

        assertEquals(expectedMessage, t.getMessage());
    }

    @Test
    void testCreateVotoWhenVotingPeriodEnded() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);

        Associado associado = new Associado();
        associado.setId(2L);

        when(pautaApplication.findById(1L)).thenReturn(pauta);
        when(associadoApplication.findById(2L)).thenReturn(associado);
        when(votoRepository.existsByPautaIdAndAssociadoId(1L, 2L)).thenReturn(Boolean.FALSE);
        when(sessaoApplication.hasPautaSessaoAberta(1L)).thenReturn(Boolean.FALSE);

        CreateVotoDTO createVoto = new CreateVotoDTO(2L, 1L, TipoEscolhaVoto.SIM.name());

        Throwable t = assertThrows(ValidationException.class, () -> createVotoUseCase.createVoto(createVoto));

        String expectedMessage = "Sessão de votação encerrada para essa pauta!";

        assertEquals(expectedMessage, t.getMessage());
    }

    @Test
    void testCreateVotoWhenShouldComputeVote() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);

        Associado associado = new Associado();
        associado.setId(2L);

        when(pautaApplication.findById(1L)).thenReturn(pauta);
        when(associadoApplication.findById(2L)).thenReturn(associado);
        when(votoRepository.existsByPautaIdAndAssociadoId(1L, 2L)).thenReturn(Boolean.FALSE);
        when(sessaoApplication.hasPautaSessaoAberta(1L)).thenReturn(Boolean.TRUE);

        CreateVotoDTO createVoto = new CreateVotoDTO(2L, 1L, TipoEscolhaVoto.SIM.name());
        Voto voto = new Voto(createVoto);
        voto.setPauta(pauta);
        voto.setAssociado(associado);

        assertEquals(new DetailVotoDTO(voto), createVotoUseCase.createVoto(createVoto));
    }
}