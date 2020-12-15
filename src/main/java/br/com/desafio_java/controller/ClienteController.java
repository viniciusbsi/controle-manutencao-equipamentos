package br.com.desafio_java.controller;

import br.com.desafio_java.dto.ClientePostDto;
import br.com.desafio_java.exception.RegistroNaoEncontradoException;
import br.com.desafio_java.mapper.ClienteMapper;
import br.com.desafio_java.model.Cliente;
import br.com.desafio_java.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Cliente")
@Api(description = "Clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @ApiOperation(value = "Buscar todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Clientes encontrados com sucesso")
    })
    @GetMapping("/clientes")
    public List<Cliente> BuscarTodos() {
        try {
            List<Cliente> listaCliente = clienteService.findAll();
            return listaCliente;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @ApiOperation(value = "Cria um Cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente criado com sucesso")
    })
    @PostMapping("/cliente")
    public Cliente IncluirCliente(@RequestBody ClientePostDto clientePostDto) {
        Cliente cliente = clienteMapper.toEntity(clientePostDto);
        clienteService.save(cliente);
        return cliente;
    }

    @ApiOperation(value = "Exclui um cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente excluído com sucesso")
    })
    @DeleteMapping("/{id}")
    public void Deletar(@PathVariable Long id) {
        clienteService.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
        clienteService.deleteById(id);
    }

    @ApiOperation(value = "Atualiza um cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente atualizado com sucesso")
    })
    @PostMapping("/atualizar/{id}")
    public Cliente UpdateCliente(@RequestBody Cliente cliente) throws RegistroNaoEncontradoException {
        clienteService.findById(cliente.id).orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
        Cliente clienteAtualizado = clienteService.save(cliente);
        return clienteAtualizado;
    }

    @ApiOperation(value = "Busca um cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public Cliente BuscarPorId(@PathVariable Long id) throws RegistroNaoEncontradoException{
        Cliente cliente = clienteService.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
        return cliente;
    }
}
