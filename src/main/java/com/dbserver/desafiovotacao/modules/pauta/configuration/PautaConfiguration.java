package com.dbserver.desafiovotacao.modules.pauta.configuration;

import com.dbserver.desafiovotacao.modules.pauta.repository.impl.PautaRepositoryImpl;
import com.dbserver.desafiovotacao.modules.pauta.usecase.CreatePautaUseCase;
import com.dbserver.desafiovotacao.modules.pauta.usecase.FindPautaUseCase;
import com.dbserver.desafiovotacao.modules.pauta.usecase.impl.CreatePautaUseCaseImpl;
import com.dbserver.desafiovotacao.modules.pauta.usecase.impl.FindPautaUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PautaConfiguration {

    @Autowired
    private PautaRepositoryImpl pautaRepository;

    @Bean
    public CreatePautaUseCase beanCreatePautaUseCase() {
        return new CreatePautaUseCaseImpl(pautaRepository);
    }

    @Bean
    public FindPautaUseCase beanFindPautaUseCase() {
        return new FindPautaUseCaseImpl(pautaRepository);
    }
}
