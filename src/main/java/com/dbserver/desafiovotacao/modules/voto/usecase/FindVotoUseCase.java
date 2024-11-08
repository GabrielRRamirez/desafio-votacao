package com.dbserver.desafiovotacao.modules.voto.usecase;

import com.dbserver.desafiovotacao.modules.voto.domain.dto.DetailResultadoVotacaoDTO;
import com.dbserver.desafiovotacao.modules.voto.domain.dto.DetailVotoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindVotoUseCase {

    Page<DetailVotoDTO> findAll(Pageable pageable);

    DetailResultadoVotacaoDTO count(long idPauta);
}
