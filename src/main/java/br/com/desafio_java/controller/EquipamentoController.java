package br.com.desafio_java.controller;

import br.com.desafio_java.dto.EquipamentoPostDto;
import br.com.desafio_java.exception.RegistroNaoEncontradoException;
import br.com.desafio_java.mapper.EquipamentoMapper;
import br.com.desafio_java.model.Equipamento;
import br.com.desafio_java.service.EquipamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamento")
@Api("Equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoService _equipamentoService;

    @Autowired
    private EquipamentoMapper _equipamentoMapper;

    @ApiOperation(value = "Buscar todos os equipamentos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Equipamentos encontrados com sucesso")
    })
    @GetMapping("/equipamentos")
    public List<Equipamento> BuscarTodos() {
        try {
            List<Equipamento> listaEquipamentos = _equipamentoService.findAll();
            return listaEquipamentos;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @ApiOperation(value = "Cria um equipamento")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Equipamento criado com sucesso")
    })
    @PostMapping()
    public Equipamento IncluirEquipamento(@RequestBody EquipamentoPostDto equipamentoPostDto) {
        try {
            Equipamento equipamento = _equipamentoMapper.toEntity(equipamentoPostDto);
            _equipamentoService.save(equipamento);
            return equipamento;
        } catch (Exception ex) {
            throw ex;
        }

    }

    @ApiOperation(value = "Exclui um equipamento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Equipamento excluído com sucesso")
    })
    @DeleteMapping("/{id}")
    public void Deletar(@PathVariable Long id) {
        _equipamentoService.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Equipamento não encontrado"));
        _equipamentoService.deleteById(id);
    }

    @ApiOperation(value = "Atualiza um equipamento")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Equipamento atualizado com sucesso")
    })
    @PostMapping("/atualizar/{id}")
    public Equipamento AtualizarEquipamento(@RequestBody Equipamento equipamento) throws RegistroNaoEncontradoException {
        _equipamentoService.findById(equipamento.id).orElseThrow(() -> new RegistroNaoEncontradoException("Equipamento não encontrado"));
        Equipamento equipamentoAtualizado = _equipamentoService.save(equipamento);
        return equipamentoAtualizado;
    }

    @ApiOperation(value = "Busca um equipamento")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Equipamento encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public Equipamento BuscarPorId(@PathVariable Long id) throws RegistroNaoEncontradoException{
        Equipamento equipamento = _equipamentoService.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Equipamento não encontrado"));
        return equipamento;
    }
}
