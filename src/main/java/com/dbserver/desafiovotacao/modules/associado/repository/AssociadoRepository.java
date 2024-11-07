package com.dbserver.desafiovotacao.modules.associado.repository;

import com.dbserver.desafiovotacao.modules.associado.domain.entity.Associado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AssociadoRepository {
    Associado save(Associado associado);

    Associado findById(long id);

    Page<Associado> findAll(Pageable pageable);

    boolean existsByCpf(String cpf);
}
