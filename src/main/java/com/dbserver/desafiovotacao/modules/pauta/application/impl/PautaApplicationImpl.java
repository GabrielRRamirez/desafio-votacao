package com.dbserver.desafiovotacao.modules.pauta.application.impl;

import com.dbserver.desafiovotacao.modules.pauta.application.PautaApplication;
import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;
import com.dbserver.desafiovotacao.modules.pauta.usecase.FindPautaUseCase;
import org.springframework.beans.factory.annotation.Autowired;

public class PautaApplicationImpl implements PautaApplication {

    @Autowired
    private FindPautaUseCase findPautaUseCase;

    @Override
    public Pauta findById(long id) {
        return findPautaUseCase.findEntityById(id);
    }
}
