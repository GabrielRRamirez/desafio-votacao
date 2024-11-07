package com.dbserver.desafiovotacao.modules.associado.usecase.impl;

import com.dbserver.desafiovotacao.infra.exceptions.ValidationException;
import com.dbserver.desafiovotacao.modules.associado.domain.dto.CreateAssociadoDTO;
import com.dbserver.desafiovotacao.modules.associado.domain.dto.DetailAssociadoDTO;
import com.dbserver.desafiovotacao.modules.associado.domain.entity.Associado;
import com.dbserver.desafiovotacao.modules.associado.repository.AssociadoRepository;
import com.dbserver.desafiovotacao.modules.associado.usecase.CreateAssociadoUsecase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateAssociadoUseCaseImpl implements CreateAssociadoUsecase {
    private final AssociadoRepository associadoRepository;

    @Override
    public DetailAssociadoDTO create(CreateAssociadoDTO createAssociado) {
        Associado associado = new Associado(createAssociado);

        if(associadoRepository.existsByCpf(associado.getCpf())) {
            throw new ValidationException("CPF já utilizado!");
        }

        associadoRepository.save(associado);
        return new DetailAssociadoDTO(associado);
    }
}
