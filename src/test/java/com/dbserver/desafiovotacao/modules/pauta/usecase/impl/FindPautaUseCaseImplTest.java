package com.dbserver.desafiovotacao.modules.pauta.usecase.impl;

import com.dbserver.desafiovotacao.modules.pauta.domain.dto.DetailPautaDTO;
import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;
import com.dbserver.desafiovotacao.modules.pauta.repository.PautaRepository;
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

class FindPautaUseCaseImplTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private FindPautaUseCaseImpl findPautaUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByIdWhenNotFound() {
        assertThrows(EntityNotFoundException.class, () -> findPautaUseCase.findById(1L));
    }

    @Test
    void testFindById() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setDescricao("PAUTA TESTE");
        when(pautaRepository.findById(1L)).thenReturn(pauta);

        assertEquals(new DetailPautaDTO(pauta), findPautaUseCase.findById(1L));
    }

    @Test
    void testFindEntityByIdWhenNotFound() {
        assertThrows(EntityNotFoundException.class, () -> findPautaUseCase.findEntityById(1L));
    }

    @Test
    void testFindEntityById() {
        Pauta expected = new Pauta();
        when(pautaRepository.findById(1L)).thenReturn(expected);

        assertEquals(expected, findPautaUseCase.findEntityById(1L));
    }

    @Test
    void testFindAll() {
        Pauta pauta = new Pauta();
        List<Pauta> pautas = List.of(pauta);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Pauta> page = new PageImpl<>(pautas, pageable, pautas.size());
        when(pautaRepository.findAll(pageable)).thenReturn(page);


        Page<DetailPautaDTO> expected = page.map(DetailPautaDTO::new);
        assertEquals(expected, findPautaUseCase.findAll(pageable));
    }
}