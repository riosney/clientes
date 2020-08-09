package com.escudo7.clientes.rest;

import com.escudo7.clientes.model.entity.Cliente;
import com.escudo7.clientes.model.repository.ClienteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("http://localhost:4200")
@Api(description = "Endpoints para criar, retornar, atualizar e deletar usuários.")
public class ClienteController {

    private final ClienteRepository repository;

    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ApiOperation("Retorna todos os usuários.")
    public List<Cliente> obterTodos(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salva usuário.")
    public Cliente salvar(@RequestBody @Valid Cliente cliente ){
        return repository.save(cliente);
    }

    @GetMapping("{id}")
    @ApiOperation("Retorna um usuário pelo seu ID.")
    public Cliente acharPorId(@ApiParam("Id do usuário. Não pode ser vazio")
                                  @PathVariable Integer id ){
        return repository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Deleta um usuário pelo seu ID.")
    public void deletar(@ApiParam("Id do usuário. Não pode ser vazio") @PathVariable Integer id ){
        repository
                .findById(id)
                .map( cliente -> {
                    repository.delete(cliente);
                    return Void.TYPE;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Atualizar o usuário pelo seu ID.")
    public void atualizar(@ApiParam("Id do usuário. Não pode ser vazio") @PathVariable Integer id,
                           @RequestBody @Valid Cliente clienteAtualizado ) {
        repository
                .findById(id)
                .map( cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setCpf(clienteAtualizado.getCpf());
                    return repository.save(cliente);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }

}
