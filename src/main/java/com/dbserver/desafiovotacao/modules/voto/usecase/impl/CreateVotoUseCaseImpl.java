package com.dbserver.desafiovotacao.modules.voto.usecase.impl;

import com.dbserver.desafiovotacao.infra.exceptions.ValidationException;
import com.dbserver.desafiovotacao.modules.associado.application.AssociadoApplication;
import com.dbserver.desafiovotacao.modules.pauta.application.PautaApplication;
import com.dbserver.desafiovotacao.modules.sessao.application.SessaoApplication;
import com.dbserver.desafiovotacao.modules.voto.domain.dto.CreateVotoDTO;
import com.dbserver.desafiovotacao.modules.voto.domain.dto.DetailVotoDTO;
import com.dbserver.desafiovotacao.modules.voto.domain.entity.Voto;
import com.dbserver.desafiovotacao.modules.voto.repository.VotoRepository;
import com.dbserver.desafiovotacao.modules.voto.usecase.CreateVotoUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateVotoUseCaseImpl implements CreateVotoUseCase {
    private final VotoRepository votoRepository;
    private final PautaApplication pautaApplication;
    private final AssociadoApplication associadoApplication;
    private final SessaoApplication sessaoApplication;

    @Override
    public DetailVotoDTO createVoto(CreateVotoDTO createVoto) {
        Voto voto = new Voto(createVoto);
        voto.setPauta(pautaApplication.findById(createVoto.idPauta()));
        voto.setAssociado(associadoApplication.findById(createVoto.idAssociado()));

        validar(voto.getPauta().getId(), voto.getAssociado().getId());

        votoRepository.save(voto);

        return new DetailVotoDTO(voto);
    }

    private void validar(long idPauta, long idAssociado) {
        if(votoRepository.existsByPautaIdAndAssociadoId(idPauta, idAssociado)) {
            throw new ValidationException("Voto já computado para o associado na pauta!");
        }

        if(!sessaoApplication.hasPautaSessaoAberta(idPauta)) {
            throw new ValidationException("Sessão de votação encerrada para essa pauta!");
        }
    }
}
