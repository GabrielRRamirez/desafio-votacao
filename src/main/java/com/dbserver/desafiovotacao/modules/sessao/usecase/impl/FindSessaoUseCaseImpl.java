package com.dbserver.desafiovotacao.modules.sessao.usecase.impl;

import com.dbserver.desafiovotacao.modules.sessao.domain.dto.DetailSessaoDTO;
import com.dbserver.desafiovotacao.modules.sessao.domain.entity.Sessao;
import com.dbserver.desafiovotacao.modules.sessao.repository.SessaoRepository;
import com.dbserver.desafiovotacao.modules.sessao.usecase.FindSessaoUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

@RequiredArgsConstructor
public class FindSessaoUseCaseImpl implements FindSessaoUseCase {
    private final SessaoRepository sessaoRepository;

    @Override
    public DetailSessaoDTO findById(long id) {
        Sessao sessao = sessaoRepository.findById(id);

        if(Objects.isNull(sessao)) {
            throw new EntityNotFoundException();
        }

        return new DetailSessaoDTO(sessao);
    }

    @Override
    public Page<DetailSessaoDTO> findAll(Pageable pageable) {
        return sessaoRepository.findAll(pageable).map(DetailSessaoDTO::new);
    }
}
