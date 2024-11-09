package com.dbserver.desafiovotacao.modules.sessao.configuration;

import com.dbserver.desafiovotacao.modules.pauta.application.PautaApplication;
import com.dbserver.desafiovotacao.modules.sessao.application.impl.SessaoApplicationImpl;
import com.dbserver.desafiovotacao.modules.sessao.repository.impl.SessaoRepositoryImpl;
import com.dbserver.desafiovotacao.modules.sessao.usecase.impl.CreateSessaoUseCaseImpl;
import com.dbserver.desafiovotacao.modules.sessao.usecase.impl.FindSessaoUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SessaoConfigurationTest {

    @Mock
    private SessaoRepositoryImpl sessaoRepository;

    @Mock
    private PautaApplication pautaApplication;

    @InjectMocks
    private SessaoConfiguration sessaoConfiguration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBeanCreateSessaoUseCase() {
        assertTrue(sessaoConfiguration.beanCreateSessaoUseCase() instanceof CreateSessaoUseCaseImpl);
    }

    @Test
    void testBeanFindSessaoUseCase() {
        assertTrue(sessaoConfiguration.beanFindSessaoUseCase() instanceof FindSessaoUseCaseImpl);
    }

    @Test
    void testBeanSessaoApplication() {
        assertTrue(sessaoConfiguration.beanSessaoApplication() instanceof SessaoApplicationImpl);
    }
}