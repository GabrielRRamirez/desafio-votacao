package com.dbserver.desafiovotacao.modules.pauta.configuration;

import com.dbserver.desafiovotacao.modules.pauta.application.impl.PautaApplicationImpl;
import com.dbserver.desafiovotacao.modules.pauta.repository.impl.PautaRepositoryImpl;
import com.dbserver.desafiovotacao.modules.pauta.usecase.impl.CreatePautaUseCaseImpl;
import com.dbserver.desafiovotacao.modules.pauta.usecase.impl.FindPautaUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PautaConfigurationTest {

    @Mock
    private PautaRepositoryImpl pautaRepository;

    @InjectMocks
    private PautaConfiguration pautaConfiguration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBeanCreatePautaUseCase() {
        assertTrue(pautaConfiguration.beanCreatePautaUseCase() instanceof CreatePautaUseCaseImpl);
    }

    @Test
    void testBeanFindPautaUseCase() {
        assertTrue(pautaConfiguration.beanFindPautaUseCase() instanceof FindPautaUseCaseImpl);
    }

    @Test
    void testBeanPautaApplication() {
        assertTrue(pautaConfiguration.beanPautaApplication() instanceof PautaApplicationImpl);
    }
}