package com.dbserver.desafiovotacao.modules.voto.usecase.impl;

import com.dbserver.desafiovotacao.infra.exceptions.ValidationException;
import com.dbserver.desafiovotacao.modules.associado.domain.entity.Associado;
import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;
import com.dbserver.desafiovotacao.modules.voto.domain.dto.DetailResultadoVotacaoDTO;
import com.dbserver.desafiovotacao.modules.voto.domain.dto.DetailVotoDTO;
import com.dbserver.desafiovotacao.modules.voto.domain.entity.Voto;
import com.dbserver.desafiovotacao.modules.voto.domain.enums.TipoEscolhaVoto;
import com.dbserver.desafiovotacao.modules.voto.domain.enums.TipoResultadoVotacao;
import com.dbserver.desafiovotacao.modules.voto.repository.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class FindVotoUseCaseImplTest {

    @Mock
    private  VotoRepository votoRepository;

    @InjectMocks
    private FindVotoUseCaseImpl findVotoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Voto voto = new Voto();
        voto.setPauta(new Pauta());
        voto.setAssociado(new Associado());
        List<Voto> votos = List.of(voto);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Voto> page = new PageImpl<>(votos, pageable, votos.size());
        when(votoRepository.findAll(pageable)).thenReturn(page);


        Page<DetailVotoDTO> expected = page.map(DetailVotoDTO::new);
        assertEquals(expected, findVotoUseCase.findAll(pageable));
    }

    @Test
    void testCountWhenNotFound() {
        assertThrows(ValidationException.class, () -> findVotoUseCase.count(1L));
    }

    @Test
    void testCountWhenPautaAprovada() {
        when(votoRepository.findByPautaId(1L)).thenReturn(getVotacaoAprovada());

        DetailResultadoVotacaoDTO resultado = new DetailResultadoVotacaoDTO(
                1L,
                "PAUTA TESTE",
                5,
                3,
                2,
                TipoResultadoVotacao.APROVADA);

        assertEquals(resultado, findVotoUseCase.count(1L));
    }

    @Test
    void testCountWhenPautaRejeitada() {
        when(votoRepository.findByPautaId(1L)).thenReturn(getVotacaoRejeitada());

        DetailResultadoVotacaoDTO resultado = new DetailResultadoVotacaoDTO(
                1L,
                "PAUTA TESTE",
                5,
                2,
                3,
                TipoResultadoVotacao.REJEITADA);

        assertEquals(resultado, findVotoUseCase.count(1L));
    }

    @Test
    void testCountWhenPautaEmpate() {
        when(votoRepository.findByPautaId(1L)).thenReturn(getVotacaoEmpate());

        DetailResultadoVotacaoDTO resultado = new DetailResultadoVotacaoDTO(
                1L,
                "PAUTA TESTE",
                4,
                2,
                2,
                TipoResultadoVotacao.EMPATE);

        assertEquals(resultado, findVotoUseCase.count(1L));
    }

    private List<Voto> getVotacaoAprovada() {
        List<Voto> votos = new ArrayList<>();
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setDescricao("PAUTA TESTE");

        Voto voto = new Voto();
        voto.setEscolha(TipoEscolhaVoto.SIM);
        voto.setPauta(pauta);
        votos.add(voto);

        voto = new Voto();
        voto.setEscolha(TipoEscolhaVoto.NAO);
        voto.setPauta(pauta);
        votos.add(voto);

        voto = new Voto();
        voto.setEscolha(TipoEscolhaVoto.SIM);
        voto.setPauta(pauta);
        votos.add(voto);

        voto = new Voto();
        voto.setEscolha(TipoEscolhaVoto.NAO);
        voto.setPauta(pauta);
        votos.add(voto);

        voto = new Voto();
        voto.setEscolha(TipoEscolhaVoto.SIM);
        voto.setPauta(pauta);
        votos.add(voto);

        return votos;
    }

    private List<Voto> getVotacaoRejeitada() {
        List<Voto> votos = new ArrayList<>();
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setDescricao("PAUTA TESTE");

        Voto voto = new Voto();
        voto.setEscolha(TipoEscolhaVoto.SIM);
        voto.setPauta(pauta);
        votos.add(voto);

        voto = new Voto();
        voto.setEscolha(TipoEscolhaVoto.NAO);
        voto.setPauta(pauta);
        votos.add(voto);

        voto = new Voto();
        voto.setEscolha(TipoEscolhaVoto.SIM);
        voto.setPauta(pauta);
        votos.add(voto);

        voto = new Voto();
        voto.setEscolha(TipoEscolhaVoto.NAO);
        voto.setPauta(pauta);
        votos.add(voto);

        voto = new Voto();
        voto.setEscolha(TipoEscolhaVoto.NAO);
        voto.setPauta(pauta);
        votos.add(voto);

        return votos;
    }

    private List<Voto> getVotacaoEmpate() {
        List<Voto> votos = new ArrayList<>();
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setDescricao("PAUTA TESTE");

        Voto voto = new Voto();
        voto.setEscolha(TipoEscolhaVoto.SIM);
        voto.setPauta(pauta);
        votos.add(voto);

        voto = new Voto();
        voto.setEscolha(TipoEscolhaVoto.NAO);
        voto.setPauta(pauta);
        votos.add(voto);

        voto = new Voto();
        voto.setEscolha(TipoEscolhaVoto.SIM);
        voto.setPauta(pauta);
        votos.add(voto);

        voto = new Voto();
        voto.setEscolha(TipoEscolhaVoto.NAO);
        voto.setPauta(pauta);
        votos.add(voto);

        return votos;
    }
}