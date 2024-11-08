package com.dbserver.desafiovotacao.modules.sessao.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record CreateSessaoDTO(
        @NotNull(message = "A data/hora de início é obrigatória!")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime dataHoraInicio,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime dataHoraTermino,

        @Positive(message = "A pauta é obrigatória!")
        long idPauta) {

}
