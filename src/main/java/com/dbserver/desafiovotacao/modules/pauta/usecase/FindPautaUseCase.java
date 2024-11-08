package com.dbserver.desafiovotacao.modules.pauta.usecase;

import com.dbserver.desafiovotacao.modules.pauta.domain.dto.DetailPautaDTO;
import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindPautaUseCase {

    DetailPautaDTO findById(long id);
    Pauta findEntityById(long id);

    Page<DetailPautaDTO> findAll(Pageable pageable);
}
