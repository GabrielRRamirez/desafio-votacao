package com.dbserver.desafiovotacao.modules.associado.usecase;

import com.dbserver.desafiovotacao.modules.associado.domain.dto.CreateAssociadoDTO;
import com.dbserver.desafiovotacao.modules.associado.domain.dto.DetailAssociadoDTO;

public interface CreateAssociadoUsecase {
    DetailAssociadoDTO create(CreateAssociadoDTO createAssociado);
}
