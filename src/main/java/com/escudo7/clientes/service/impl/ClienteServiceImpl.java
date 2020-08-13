package com.escudo7.clientes.service.impl;

import com.escudo7.clientes.exception.RegraNegocioException;
import com.escudo7.clientes.model.repository.ClienteRepository;
import com.escudo7.clientes.service.ClienteService;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository){
        super();
        this.repository = repository;
    }

    @Override
    public void validarCpf(String cpf) {
        boolean existe = repository.existsByCpf(cpf);
        if(existe){
            throw new RegraNegocioException("CPF existente.");
        }
    }
}
