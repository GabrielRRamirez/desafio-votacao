package com.dbserver.desafiovotacao.modules.associado.configuration;

import com.dbserver.desafiovotacao.modules.associado.repository.impl.AssociadoRepositoryImpl;
import com.dbserver.desafiovotacao.modules.associado.usecase.CreateAssociadoUsecase;
import com.dbserver.desafiovotacao.modules.associado.usecase.FindAssociadoUseCase;
import com.dbserver.desafiovotacao.modules.associado.usecase.impl.CreateAssociadoUseCaseImpl;
import com.dbserver.desafiovotacao.modules.associado.usecase.impl.FindAssociadoUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssociadoConfiguration {

    @Autowired
    private AssociadoRepositoryImpl associadoRepository;

    @Bean
    public CreateAssociadoUsecase beanCreateAssociadoUsecase() {
        return new CreateAssociadoUseCaseImpl(associadoRepository);
    }

    @Bean
    public FindAssociadoUseCase beanFindAssociadoUseCase() {
        return new FindAssociadoUseCaseImpl(associadoRepository);
    }
}
