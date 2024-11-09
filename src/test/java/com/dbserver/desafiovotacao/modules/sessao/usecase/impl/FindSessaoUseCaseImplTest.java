package com.dbserver.desafiovotacao.modules.sessao.usecase.impl;

import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;
import com.dbserver.desafiovotacao.modules.sessao.domain.dto.DetailSessaoDTO;
import com.dbserver.desafiovotacao.modules.sessao.domain.entity.Sessao;
import com.dbserver.desafiovotacao.modules.sessao.repository.SessaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class FindSessaoUseCaseImplTest {

    @Mock
    private SessaoRepository sessaoRepository;

    @InjectMocks
    private FindSessaoUseCaseImpl findSessaoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByIdWhenNotFound() {
        assertThrows(EntityNotFoundException.class, () -> findSessaoUseCase.findById(1L));
    }

    @Test
    void testFindById() {
        Sessao sessao = new Sessao();
        sessao.setId(1L);
        sessao.setPauta(new Pauta());

        when(sessaoRepository.findById(1L)).thenReturn(sessao);

        assertEquals(new DetailSessaoDTO(sessao), findSessaoUseCase.findById(1L));
    }

    @Test
    void testFindByPautaId() {
        Sessao sessao = new Sessao();
        sessao.setId(1L);
        sessao.setPauta(new Pauta());

        when(sessaoRepository.findByPautaId(1L)).thenReturn(sessao);

        assertEquals(sessao, findSessaoUseCase.findByPautaId(1L));
    }

    @Test
    void testFindAll() {
        Sessao sessao = new Sessao();
        sessao.setPauta(new Pauta());
        List<Sessao> sessoes = List.of(sessao);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Sessao> page = new PageImpl<>(sessoes, pageable, sessoes.size());
        when(sessaoRepository.findAll(pageable)).thenReturn(page);


        Page<DetailSessaoDTO> expected = page.map(DetailSessaoDTO::new);
        assertEquals(expected, findSessaoUseCase.findAll(pageable));
    }
}