package com.dbserver.desafiovotacao.modules.associado.usecase;

import com.dbserver.desafiovotacao.modules.associado.domain.dto.DetailAssociadoDTO;
import com.dbserver.desafiovotacao.modules.associado.domain.entity.Associado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAssociadoUseCase {

    DetailAssociadoDTO findById(long id);

    Associado findEntityById(long id);

    Page<DetailAssociadoDTO> findAll(Pageable pageable);
}
