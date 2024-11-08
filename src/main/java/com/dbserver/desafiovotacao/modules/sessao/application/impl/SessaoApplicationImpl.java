package com.dbserver.desafiovotacao.modules.sessao.application.impl;

import com.dbserver.desafiovotacao.modules.sessao.application.SessaoApplication;
import com.dbserver.desafiovotacao.modules.sessao.domain.entity.Sessao;
import com.dbserver.desafiovotacao.modules.sessao.usecase.FindSessaoUseCase;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessaoApplicationImpl implements SessaoApplication {

    @Autowired
    private FindSessaoUseCase findSessaoUseCase;

    @Override
    public boolean hasPautaSessaoAberta(long idPauta) {
        Sessao sessao = findSessaoUseCase.findByPautaId(idPauta);

        if(Objects.isNull(sessao)) {
            return false;
        }

        return isPeriodoValido(sessao);
    }

    private boolean isPeriodoValido(Sessao sessao) {
        LocalDateTime dataAtual = LocalDateTime.now();

        if(!dataAtual.isAfter(sessao.getDataHoraInicio()) && !dataAtual.isEqual(sessao.getDataHoraInicio())) {
            return Boolean.FALSE;
        }

        if(!dataAtual.isBefore(sessao.getDataHoraTermino()) && !dataAtual.isEqual(sessao.getDataHoraTermino())) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
