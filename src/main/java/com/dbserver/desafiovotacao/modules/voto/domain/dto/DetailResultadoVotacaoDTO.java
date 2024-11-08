package com.dbserver.desafiovotacao.modules.voto.domain.dto;

import com.dbserver.desafiovotacao.modules.voto.domain.enums.TipoResultadoVotacao;

public record DetailResultadoVotacaoDTO(
        long idPauta,
        String descricaoPauta,
        long totalVotos,
        long totalSim,
        long totalNao,
        TipoResultadoVotacao resultado) {
}
