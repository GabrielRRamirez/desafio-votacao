package com.dbserver.desafiovotacao.modules.voto.repository;

import com.dbserver.desafiovotacao.modules.voto.domain.entity.Voto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VotoRepository {

    Voto save(Voto voto);

    boolean existsByPautaIdAndAssociadoId(long idPauta, long idAssociado);

    Page<Voto> findAll(Pageable pageable);

    List<Voto> findByPautaId(long idPauta);
}
