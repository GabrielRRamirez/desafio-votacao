package com.dbserver.desafiovotacao.modules.associado.usecase.impl;

import com.dbserver.desafiovotacao.modules.associado.domain.dto.DetailAssociadoDTO;
import com.dbserver.desafiovotacao.modules.associado.domain.entity.Associado;
import com.dbserver.desafiovotacao.modules.associado.repository.AssociadoRepository;
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

class FindAssociadoUseCaseImplTest {

    @Mock
    private AssociadoRepository associadoRepository;

    @InjectMocks
    private FindAssociadoUseCaseImpl findAssociadoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByIdWhenNotFound() {
        assertThrows(EntityNotFoundException.class, () -> findAssociadoUseCase.findById(1L));
    }

    @Test
    void testFindById() {
        Associado associado = new Associado();
        associado.setId(1L);
        associado.setNome("TESTE");
        associado.setCpf("123456789012");

        when(associadoRepository.findById(1L)).thenReturn(associado);

        assertEquals(new DetailAssociadoDTO(associado), findAssociadoUseCase.findById(1L));
    }

    @Test
    void testFindEntityById() {
        Associado associado = new Associado();
        associado.setId(1L);
        associado.setNome("TESTE");
        associado.setCpf("123456789012");

        when(associadoRepository.findById(1L)).thenReturn(associado);

        assertEquals(associado, findAssociadoUseCase.findEntityById(1L));
    }

    @Test
    void testFindEntityByIdWhenNotFound() {
        assertThrows(EntityNotFoundException.class, () -> findAssociadoUseCase.findEntityById(1L));
    }

    @Test
    void testFindAll() {
        Associado associado1 = new Associado();
        List<Associado> associados = List.of(associado1);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Associado> page = new PageImpl<>(associados, pageable, associados.size());
        when(associadoRepository.findAll(pageable)).thenReturn(page);


        Page<DetailAssociadoDTO> expected = page.map(DetailAssociadoDTO::new);
        assertEquals(expected, findAssociadoUseCase.findAll(pageable));
    }
}