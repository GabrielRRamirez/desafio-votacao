package com.dbserver.desafiovotacao.modules.associado.application;

import com.dbserver.desafiovotacao.modules.associado.domain.entity.Associado;

public interface AssociadoApplication {

    Associado findById(long id);
}
