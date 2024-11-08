package com.dbserver.desafiovotacao.modules.voto.usecase;

import com.dbserver.desafiovotacao.modules.voto.domain.dto.CreateVotoDTO;
import com.dbserver.desafiovotacao.modules.voto.domain.dto.DetailVotoDTO;

public interface CreateVotoUseCase {

    DetailVotoDTO createVoto(CreateVotoDTO createVoto);
}
