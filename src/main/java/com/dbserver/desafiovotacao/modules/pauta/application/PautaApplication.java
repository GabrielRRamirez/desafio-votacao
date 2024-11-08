package com.dbserver.desafiovotacao.modules.pauta.application;

import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;

public interface PautaApplication {

    Pauta findById(long id);
}
