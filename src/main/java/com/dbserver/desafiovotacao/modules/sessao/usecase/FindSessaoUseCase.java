package com.dbserver.desafiovotacao.modules.sessao.usecase;

import com.dbserver.desafiovotacao.modules.sessao.domain.dto.DetailSessaoDTO;
import com.dbserver.desafiovotacao.modules.sessao.domain.entity.Sessao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindSessaoUseCase {

    DetailSessaoDTO findById(long id);

    Sessao findByPautaId(long idPauta);

    Page<DetailSessaoDTO> findAll(Pageable pageable);
}
