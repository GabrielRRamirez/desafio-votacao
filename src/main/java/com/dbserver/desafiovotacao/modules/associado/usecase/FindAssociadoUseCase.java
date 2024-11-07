package com.dbserver.desafiovotacao.modules.associado.usecase;

import com.dbserver.desafiovotacao.modules.associado.domain.dto.DetailAssociadoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAssociadoUseCase {

    DetailAssociadoDTO findById(long pId);

    Page<DetailAssociadoDTO> findAll(Pageable pageable);
}
