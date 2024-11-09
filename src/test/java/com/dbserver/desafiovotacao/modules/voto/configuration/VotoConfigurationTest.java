package com.dbserver.desafiovotacao.modules.voto.configuration;

import com.dbserver.desafiovotacao.modules.associado.application.AssociadoApplication;
import com.dbserver.desafiovotacao.modules.pauta.application.PautaApplication;
import com.dbserver.desafiovotacao.modules.sessao.application.SessaoApplication;
import com.dbserver.desafiovotacao.modules.voto.repository.impl.VotoRepositoryImpl;
import com.dbserver.desafiovotacao.modules.voto.usecase.impl.CreateVotoUseCaseImpl;
import com.dbserver.desafiovotacao.modules.voto.usecase.impl.FindVotoUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;

class VotoConfigurationTest {

    @Mock
    private VotoRepositoryImpl votoRepository;

    @Mock
    private PautaApplication pautaApplication;

    @Mock
    private AssociadoApplication associadoApplication;

    @Mock
    private SessaoApplication sessaoApplication;

    @InjectMocks
    private VotoConfiguration votoConfiguration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBeanCreateVotoUseCase() {
        assertTrue(votoConfiguration.beanCreateVotoUseCase() instanceof CreateVotoUseCaseImpl);
    }

    @Test
    void testBeanFindVotoUseCase() {
        assertTrue(votoConfiguration.beanFindVotoUseCase() instanceof FindVotoUseCaseImpl);
    }
}