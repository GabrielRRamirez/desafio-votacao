package com.dbserver.desafiovotacao.modules.voto.repository.impl;

import com.dbserver.desafiovotacao.modules.voto.domain.entity.Voto;
import com.dbserver.desafiovotacao.modules.voto.repository.VotoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepositoryImpl extends JpaRepository<Voto, Long>, VotoRepository {
}
