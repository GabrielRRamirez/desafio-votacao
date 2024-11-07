package com.dbserver.desafiovotacao.modules.associado.repository.impl;

import com.dbserver.desafiovotacao.modules.associado.domain.entity.Associado;
import com.dbserver.desafiovotacao.modules.associado.repository.AssociadoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociadoRepositoryImpl extends JpaRepository<Associado, Long>, AssociadoRepository {
}
