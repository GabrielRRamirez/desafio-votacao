package com.dbserver.desafiovotacao.modules.sessao.repository.impl;

import com.dbserver.desafiovotacao.modules.sessao.domain.entity.Sessao;
import com.dbserver.desafiovotacao.modules.sessao.repository.SessaoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoRepositoryImpl extends JpaRepository<Sessao, Long>, SessaoRepository {
}
