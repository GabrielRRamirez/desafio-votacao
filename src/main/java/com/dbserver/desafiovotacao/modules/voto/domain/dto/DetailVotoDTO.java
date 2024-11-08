package com.dbserver.desafiovotacao.modules.voto.domain.dto;

import com.dbserver.desafiovotacao.modules.voto.domain.entity.Voto;
import com.dbserver.desafiovotacao.modules.voto.domain.enums.TipoEscolhaVoto;

public record DetailVotoDTO(
        long id,
        long idAssociado,
        long idPauta,
        TipoEscolhaVoto escolha) {

    public DetailVotoDTO(Voto voto) {
        this(
                voto.getId(),
                voto.getAssociado().getId(),
                voto.getPauta().getId(),
                voto.getEscolha());
    }
}
