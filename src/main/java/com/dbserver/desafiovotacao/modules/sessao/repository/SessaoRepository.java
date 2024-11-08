package com.dbserver.desafiovotacao.modules.sessao.repository;

import com.dbserver.desafiovotacao.modules.sessao.domain.entity.Sessao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SessaoRepository {

    Sessao save(Sessao sessao);

    Sessao findById(long id);

    Page<Sessao> findAll(Pageable pageable);

    boolean existsByPautaId(long idPauta);
}
