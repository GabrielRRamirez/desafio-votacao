package com.dbserver.desafiovotacao.modules.sessao.domain.dto;

import com.dbserver.desafiovotacao.modules.sessao.domain.entity.Sessao;

import java.time.LocalDateTime;

public record DetailSessaoDTO(
        long id,
        LocalDateTime dataHoraInicio,
        LocalDateTime dataHoraTermino,
        long idPauta,
        String descricaoPauta) {

    public DetailSessaoDTO(Sessao sessao) {
        this(
                sessao.getId(),
                sessao.getDataHoraInicio(),
                sessao.getDataHoraTermino(),
                sessao.getPauta().getId(),
                sessao.getPauta().getDescricao());
    }
}
