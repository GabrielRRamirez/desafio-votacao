package com.dbserver.desafiovotacao.modules.pauta.usecase;

import com.dbserver.desafiovotacao.modules.pauta.domain.dto.CreatePautaDTO;
import com.dbserver.desafiovotacao.modules.pauta.domain.dto.DetailPautaDTO;

public interface CreatePautaUseCase {

    DetailPautaDTO create(CreatePautaDTO createPautaDTO);
}
