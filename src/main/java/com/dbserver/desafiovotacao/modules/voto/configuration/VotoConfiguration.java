package com.dbserver.desafiovotacao.modules.voto.configuration;

import com.dbserver.desafiovotacao.modules.associado.application.AssociadoApplication;
import com.dbserver.desafiovotacao.modules.pauta.application.PautaApplication;
import com.dbserver.desafiovotacao.modules.sessao.application.SessaoApplication;
import com.dbserver.desafiovotacao.modules.voto.repository.impl.VotoRepositoryImpl;
import com.dbserver.desafiovotacao.modules.voto.usecase.CreateVotoUseCase;
import com.dbserver.desafiovotacao.modules.voto.usecase.FindVotoUseCase;
import com.dbserver.desafiovotacao.modules.voto.usecase.impl.CreateVotoUseCaseImpl;
import com.dbserver.desafiovotacao.modules.voto.usecase.impl.FindVotoUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VotoConfiguration {

    @Autowired
    private VotoRepositoryImpl votoRepository;

    @Autowired
    private PautaApplication pautaApplication;

    @Autowired
    private AssociadoApplication associadoApplication;

    @Autowired
    private SessaoApplication sessaoApplication;

    @Bean
    public CreateVotoUseCase beanCreateVotoUseCase() {
        return new CreateVotoUseCaseImpl(votoRepository, pautaApplication, associadoApplication, sessaoApplication);
    }

    @Bean
    public FindVotoUseCase beanFindVotoUseCase() {
        return new FindVotoUseCaseImpl(votoRepository);
    }
}
