package com.dbserver.desafiovotacao.modules.sessao.usecase;

import com.dbserver.desafiovotacao.modules.sessao.domain.dto.DetailSessaoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindSessaoUseCase {

    DetailSessaoDTO findById(long id);

    Page<DetailSessaoDTO> findAll(Pageable pageable);
}
