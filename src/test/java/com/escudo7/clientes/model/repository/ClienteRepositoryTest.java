package com.escudo7.clientes.model.repository;

import com.escudo7.clientes.model.entity.Cliente;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ClienteRepositoryTest {

    @Autowired
    ClienteRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void verificarEmail(){

        ClienteRepository clienteRepository = Mockito.mock(ClienteRepository.class);

        Cliente cliente = criarCliente();
        entityManager.persist(cliente);

        boolean result = repository.existsByCpf("53798419027");

        Assertions.assertThat(result).isTrue();
    }

    public static Cliente criarCliente() {
        LocalDate data = LocalDate.parse("1990-05-01");

        return Cliente.builder()
                .nome("riosney")
                .cpf("53798419027")
                .dataNascimento(data).build();
    }

}
