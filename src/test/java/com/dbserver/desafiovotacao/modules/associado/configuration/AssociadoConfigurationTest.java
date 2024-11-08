package com.dbserver.desafiovotacao.modules.associado.configuration;

import com.dbserver.desafiovotacao.modules.associado.application.impl.AssociadoApplicationImpl;
import com.dbserver.desafiovotacao.modules.associado.repository.impl.AssociadoRepositoryImpl;
import com.dbserver.desafiovotacao.modules.associado.usecase.impl.CreateAssociadoUseCaseImpl;
import com.dbserver.desafiovotacao.modules.associado.usecase.impl.FindAssociadoUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AssociadoConfigurationTest {

    @Mock
    private AssociadoRepositoryImpl associadoRepository;

    @InjectMocks
    private AssociadoConfiguration associadoConfiguration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBeanCreateAssociadoUsecase() {
        assertTrue(associadoConfiguration.beanCreateAssociadoUsecase() instanceof CreateAssociadoUseCaseImpl);
    }

    @Test
    void testBeanFindAssociadoUseCase() {
        assertTrue(associadoConfiguration.beanFindAssociadoUseCase() instanceof FindAssociadoUseCaseImpl);
    }

    @Test
    void testBeanAssociadoApplication() {
        assertTrue(associadoConfiguration.beanAssociadoApplication() instanceof AssociadoApplicationImpl);
    }
}