package com.dbserver.desafiovotacao.modules.pauta.usecase;

import com.dbserver.desafiovotacao.modules.pauta.domain.dto.DetailPautaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindPautaUseCase {

    DetailPautaDTO findById(long id);

    Page<DetailPautaDTO> findAll(Pageable pageable);
}
