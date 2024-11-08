package com.dbserver.desafiovotacao.modules.associado.usecase.impl;

import com.dbserver.desafiovotacao.modules.associado.domain.dto.DetailAssociadoDTO;
import com.dbserver.desafiovotacao.modules.associado.domain.entity.Associado;
import com.dbserver.desafiovotacao.modules.associado.repository.AssociadoRepository;
import com.dbserver.desafiovotacao.modules.associado.usecase.FindAssociadoUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

@RequiredArgsConstructor
public class FindAssociadoUseCaseImpl implements FindAssociadoUseCase {
    private final AssociadoRepository associadoRepository;

    @Override
    public DetailAssociadoDTO findById(long id) {
        Associado associado = associadoRepository.findById(id);
        if (Objects.isNull(associado)) {
            throw new EntityNotFoundException();
        }

        return new DetailAssociadoDTO(associado);
    }

    @Override
    public Associado findEntityById(long id) {
        Associado associado = associadoRepository.findById(id);
        if (Objects.isNull(associado)) {
            throw new EntityNotFoundException();
        }

        return associado;
    }

    @Override
    public Page<DetailAssociadoDTO> findAll(Pageable pageable) {
        return associadoRepository.findAll(pageable).map(DetailAssociadoDTO::new);
    }
}
