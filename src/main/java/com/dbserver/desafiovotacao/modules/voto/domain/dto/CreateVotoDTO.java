package com.dbserver.desafiovotacao.modules.voto.domain.dto;

import com.dbserver.desafiovotacao.modules.shared.EnumValidator;
import com.dbserver.desafiovotacao.modules.voto.domain.enums.TipoEscolhaVoto;
import jakarta.validation.constraints.Positive;

public record CreateVotoDTO(
        @Positive(message = "É obrigatório informar um associado!")
        long idAssociado,

        @Positive(message = "É obrigatório informar uma pauta!")
        long idPauta,

        @EnumValidator(enumClass = TipoEscolhaVoto.class, message = "Opção de voto inválida!")
        String escolha) {
}
