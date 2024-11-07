package com.dbserver.desafiovotacao.modules.pauta.repository;

import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PautaRepository {

    Pauta save(Pauta pauta);

    Pauta findById(long id);

    Page<Pauta> findAll(Pageable pageable);
}
