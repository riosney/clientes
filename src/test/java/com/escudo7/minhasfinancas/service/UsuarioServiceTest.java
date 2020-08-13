package com.escudo7.minhasfinancas.service;

import com.escudo7.clientes.exception.RegraNegocioException;
import com.escudo7.clientes.model.repository.ClienteRepository;
import com.escudo7.clientes.service.ClienteService;
import com.escudo7.clientes.service.impl.ClienteServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    ClienteService service;

    @MockBean
    ClienteRepository repository;

    @Before
    public void setUp(){
        service = new ClienteServiceImpl(repository);
    }

    @Test(expected = Test.None.class)
    public void deveValidarEmail(){
        Mockito.when(repository.existsByCpf(Mockito.anyString())).thenReturn(false);

        service.validarCpf("53798419027");
    }

    @Test(expected = RegraNegocioException.class)
    public void erroAoValidarCpfCadastrado(){
        Mockito.when(repository.existsByCpf(Mockito.anyString())).thenReturn(true);

        service.validarCpf("53798419027");
    }

}
