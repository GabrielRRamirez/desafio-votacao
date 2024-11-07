package com.dbserver.desafiovotacao.modules.associado.domain.dto;

import com.dbserver.desafiovotacao.modules.associado.domain.entity.Associado;

public record DetailAssociadoDTO(
        long id,
        String nome,
        String cpf) {

    public DetailAssociadoDTO(Associado associado) {
        this(
                associado.getId(),
                associado.getNome(),
                associado.getCpf());
    }
}
