package com.dbserver.desafiovotacao.modules.voto.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TipoEscolhaVoto {
    SIM("Sim"),
    NAO("Não");

    private final String valor;
}
