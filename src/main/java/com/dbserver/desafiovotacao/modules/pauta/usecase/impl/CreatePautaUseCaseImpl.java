package com.dbserver.desafiovotacao.modules.pauta.usecase.impl;

import com.dbserver.desafiovotacao.modules.pauta.domain.dto.CreatePautaDTO;
import com.dbserver.desafiovotacao.modules.pauta.domain.dto.DetailPautaDTO;
import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;
import com.dbserver.desafiovotacao.modules.pauta.repository.PautaRepository;
import com.dbserver.desafiovotacao.modules.pauta.usecase.CreatePautaUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreatePautaUseCaseImpl implements CreatePautaUseCase {
    private final PautaRepository pautaRepository;

    @Override
    public DetailPautaDTO create(CreatePautaDTO createPautaDTO) {
        Pauta pauta = new Pauta(createPautaDTO);
        pautaRepository.save(pauta);
        return new DetailPautaDTO(pauta);
    }
}
