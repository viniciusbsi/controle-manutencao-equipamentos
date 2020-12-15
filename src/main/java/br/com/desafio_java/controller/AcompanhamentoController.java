package br.com.desafio_java.controller;

import br.com.desafio_java.dto.AcompanhamentoPostDto;
import br.com.desafio_java.dto.AcompanhamentoUpdateDto;
import br.com.desafio_java.enums.EnumStatusAcompanhamento;
import br.com.desafio_java.exception.RegistroNaoEncontradoException;
import br.com.desafio_java.model.Acompanhamento;
import br.com.desafio_java.service.AcompanhamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Acompanhamento")
@Api(description = "Acompanhamentos")
public class AcompanhamentoController {

    @Autowired
    private AcompanhamentoService acompanhamentoService;

    @ApiOperation(value = "Buscar todos os acompanhamentos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Acompanhamentos encontrados com sucesso")
    })
    @GetMapping("/acompanhamentos")
    public List<Acompanhamento> BuscarTodos() {
        try {
            List<Acompanhamento> listaAcompanhamentos = acompanhamentoService.findAll();
            return listaAcompanhamentos;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @ApiOperation(value = "Cria um acompanhamento")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Acompanhamento criado com sucesso")
    })
    @PostMapping("/acompanhamento")
    public Acompanhamento IncluirAcompanhamento(@RequestBody AcompanhamentoPostDto acompanhamentoPostDto) {
        return acompanhamentoService.IncluirAcompanhamento(acompanhamentoPostDto);
    }

    @ApiOperation(value = "Exclui um acompanhamento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Acompanhamento excluído com sucesso")
    })
    @DeleteMapping("/{id}")
    public void Deletar(@PathVariable Long id) {
        acompanhamentoService.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Acompanhamento não encontrado"));
        acompanhamentoService.deleteById(id);
    }

    @ApiOperation(value = "Atualiza um acompanhamento")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Acompanhamento atualizado com sucesso")
    })
    @PostMapping("/atualizar/{id}")
    public Acompanhamento UpdateAcompanhamento(@RequestBody AcompanhamentoUpdateDto acompanhamentoUpdateDto) throws RegistroNaoEncontradoException {
        return acompanhamentoService.AtualizarAcompanhamento(acompanhamentoUpdateDto);
    }

    @ApiOperation(value = "Busca os acompanhamentos por colaborador e status do acompanhamento")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Acompanhamentos encontrados com sucesso")
    })
    @GetMapping("/{colaboradorId}")
    public List<Acompanhamento> BuscarAcompanhamentoPorColaboradorEStatus(@PathVariable Long colaboradorId, @RequestHeader EnumStatusAcompanhamento statusAcompanhamento) throws RegistroNaoEncontradoException{
        List<Acompanhamento> acompanhamentos = acompanhamentoService.BuscarAcompanhamentoPorColaboradorEStatus(colaboradorId, statusAcompanhamento);
        return acompanhamentos;
    }

    @ApiOperation(value = "Busca os acompanhamentos por status do acompanhamento")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Acompanhamentos encontrados com sucesso")
    })
    @GetMapping("/status")
    public List<Acompanhamento> BuscarAcompanhamentoPorStatus(@RequestHeader EnumStatusAcompanhamento statusAcompanhamento) throws RegistroNaoEncontradoException{
        List<Acompanhamento> acompanhamentos = acompanhamentoService.BuscarAcompanhamentoPorStatus(statusAcompanhamento);
        return acompanhamentos;
    }
}
