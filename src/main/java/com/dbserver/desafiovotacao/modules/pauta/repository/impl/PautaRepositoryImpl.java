package com.dbserver.desafiovotacao.modules.pauta.repository.impl;

import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;
import com.dbserver.desafiovotacao.modules.pauta.repository.PautaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepositoryImpl extends JpaRepository<Pauta, Long>, PautaRepository {
}
