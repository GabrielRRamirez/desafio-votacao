package com.dbserver.desafiovotacao.modules.pauta.usecase.impl;

import com.dbserver.desafiovotacao.modules.pauta.domain.dto.DetailPautaDTO;
import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;
import com.dbserver.desafiovotacao.modules.pauta.repository.PautaRepository;
import com.dbserver.desafiovotacao.modules.pauta.usecase.FindPautaUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

@RequiredArgsConstructor
public class FindPautaUseCaseImpl implements FindPautaUseCase {
    private final PautaRepository pautaRepository;

    @Override
    public DetailPautaDTO findById(long id) {
        Pauta pauta = pautaRepository.findById(id);

        if (Objects.isNull(pauta)) {
            throw new EntityNotFoundException();
        }

        return new DetailPautaDTO(pauta);
    }

    @Override
    public Pauta findEntityById(long id) {
        Pauta pauta = pautaRepository.findById(id);

        if (Objects.isNull(pauta)) {
            throw new EntityNotFoundException();
        }

        return pauta;
    }

    @Override
    public Page<DetailPautaDTO> findAll(Pageable pageable) {
        return pautaRepository.findAll(pageable).map(DetailPautaDTO::new);
    }
}
