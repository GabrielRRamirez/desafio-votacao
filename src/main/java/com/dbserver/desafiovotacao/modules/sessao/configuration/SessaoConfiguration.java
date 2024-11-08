package com.dbserver.desafiovotacao.modules.sessao.configuration;

import com.dbserver.desafiovotacao.modules.pauta.application.PautaApplication;
import com.dbserver.desafiovotacao.modules.sessao.application.SessaoApplication;
import com.dbserver.desafiovotacao.modules.sessao.application.impl.SessaoApplicationImpl;
import com.dbserver.desafiovotacao.modules.sessao.repository.impl.SessaoRepositoryImpl;
import com.dbserver.desafiovotacao.modules.sessao.usecase.CreateSessaoUseCase;
import com.dbserver.desafiovotacao.modules.sessao.usecase.FindSessaoUseCase;
import com.dbserver.desafiovotacao.modules.sessao.usecase.impl.CreateSessaoUseCaseImpl;
import com.dbserver.desafiovotacao.modules.sessao.usecase.impl.FindSessaoUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessaoConfiguration {

    @Autowired
    private SessaoRepositoryImpl sessaoRepository;

    @Autowired
    private PautaApplication pautaApplication;

    @Bean
    public CreateSessaoUseCase beanCreateSessaoUseCase() {
        return new CreateSessaoUseCaseImpl(sessaoRepository, pautaApplication);
    }

    @Bean
    public FindSessaoUseCase beanFindSessaoUseCase() {
        return new FindSessaoUseCaseImpl(sessaoRepository);
    }

    @Bean
    public SessaoApplication beanSessaoApplication() {
        return new SessaoApplicationImpl();
    }
}
