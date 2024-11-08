package com.dbserver.desafiovotacao.modules.sessao.usecase;

import com.dbserver.desafiovotacao.modules.sessao.domain.dto.CreateSessaoDTO;
import com.dbserver.desafiovotacao.modules.sessao.domain.dto.DetailSessaoDTO;

public interface CreateSessaoUseCase {

    DetailSessaoDTO createSessao(CreateSessaoDTO createSessaoDTO);
}
