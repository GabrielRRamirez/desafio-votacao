package com.dbserver.desafiovotacao.modules.pauta.domain.dto;

import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;

public record DetailPautaDTO(
        long id,
        String descricao) {
    public DetailPautaDTO(Pauta pauta) {
        this(
                pauta.getId(),
                pauta.getDescricao());
    }
}
