package com.dbserver.desafiovotacao.modules.voto.usecase.impl;

import com.dbserver.desafiovotacao.infra.exceptions.ValidationException;
import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;
import com.dbserver.desafiovotacao.modules.voto.domain.dto.DetailResultadoVotacaoDTO;
import com.dbserver.desafiovotacao.modules.voto.domain.dto.DetailVotoDTO;
import com.dbserver.desafiovotacao.modules.voto.domain.entity.Voto;
import com.dbserver.desafiovotacao.modules.voto.domain.enums.TipoEscolhaVoto;
import com.dbserver.desafiovotacao.modules.voto.domain.enums.TipoResultadoVotacao;
import com.dbserver.desafiovotacao.modules.voto.repository.VotoRepository;
import com.dbserver.desafiovotacao.modules.voto.usecase.FindVotoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class FindVotoUseCaseImpl implements FindVotoUseCase {
    private final VotoRepository votoRepository;

    @Override
    public Page<DetailVotoDTO> findAll(Pageable pageable) {
        return votoRepository.findAll(pageable).map(DetailVotoDTO::new);
    }

    public DetailResultadoVotacaoDTO count(long idPauta) {
        List<Voto> votos = votoRepository.findByPautaId(idPauta);

        if (votos.isEmpty()) {
            throw new ValidationException("Nenhum voto encontrado para a pauta " + idPauta);
        }

        Pauta pauta = votos.stream().findFirst().get().getPauta();
        long totalSim = votos.stream().filter(voto -> voto.getEscolha().equals(TipoEscolhaVoto.SIM)).count();
        long totalNao = votos.stream().filter(voto -> voto.getEscolha().equals(TipoEscolhaVoto.NAO)).count();

        return new DetailResultadoVotacaoDTO(
                pauta.getId(),
                pauta.getDescricao(),
                votos.size(),
                totalSim,
                totalNao,
                getResultadoVotacao(totalSim, totalNao));
    }

    private TipoResultadoVotacao getResultadoVotacao(long totalSim, long totalNao) {
        if (totalNao > totalSim) {
            return TipoResultadoVotacao.REJEITADA;
        }

        if (totalNao == totalSim) {
            return TipoResultadoVotacao.EMPATE;
        }

        return TipoResultadoVotacao.APROVADA;
    }
}
