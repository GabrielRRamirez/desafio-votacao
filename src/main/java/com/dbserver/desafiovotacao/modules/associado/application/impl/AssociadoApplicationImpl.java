package com.dbserver.desafiovotacao.modules.associado.application.impl;

import com.dbserver.desafiovotacao.modules.associado.application.AssociadoApplication;
import com.dbserver.desafiovotacao.modules.associado.domain.entity.Associado;
import com.dbserver.desafiovotacao.modules.associado.usecase.FindAssociadoUseCase;
import org.springframework.beans.factory.annotation.Autowired;

public class AssociadoApplicationImpl implements AssociadoApplication {
    @Autowired
    private FindAssociadoUseCase findAssociadoUseCase;

    @Override
    public Associado findById(long id) {
        return findAssociadoUseCase.findEntityById(id);
    }
}
