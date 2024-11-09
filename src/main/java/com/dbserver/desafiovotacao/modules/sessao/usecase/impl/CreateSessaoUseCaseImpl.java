package com.dbserver.desafiovotacao.modules.sessao.usecase.impl;

import com.dbserver.desafiovotacao.infra.exceptions.ValidationException;
import com.dbserver.desafiovotacao.modules.pauta.application.PautaApplication;
import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;
import com.dbserver.desafiovotacao.modules.sessao.domain.dto.CreateSessaoDTO;
import com.dbserver.desafiovotacao.modules.sessao.domain.dto.DetailSessaoDTO;
import com.dbserver.desafiovotacao.modules.sessao.domain.entity.Sessao;
import com.dbserver.desafiovotacao.modules.sessao.repository.SessaoRepository;
import com.dbserver.desafiovotacao.modules.sessao.usecase.CreateSessaoUseCase;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class CreateSessaoUseCaseImpl implements CreateSessaoUseCase {
    private final SessaoRepository sessaoRepository;
    private final PautaApplication pautaApplication;

    @Override
    public DetailSessaoDTO createSessao(CreateSessaoDTO createSessaoDTO) {
        Sessao sessao = new Sessao(createSessaoDTO);

        if (sessaoRepository.existsByPautaId(createSessaoDTO.idPauta())) {
            throw new ValidationException("A pauta já possui uma sessão!");
        }

        if(Objects.isNull(sessao.getDataHoraTermino())) {
            sessao.setDataHoraTermino(sessao.getDataHoraInicio().plusMinutes(1L));
        }

        if(sessao.getDataHoraTermino().isBefore(sessao.getDataHoraInicio())){
            throw new ValidationException("A data término não pode ser inferior à data início!");
        }

        Pauta pauta = pautaApplication.findById(createSessaoDTO.idPauta());
        sessao.setPauta(pauta);

        sessaoRepository.save(sessao);

        return new DetailSessaoDTO(sessao);
    }
}
