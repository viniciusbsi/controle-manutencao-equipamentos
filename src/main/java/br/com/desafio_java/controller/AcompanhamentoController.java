package br.com.desafio_java.controller;

import br.com.desafio_java.dto.AcompanhamentoPostDto;
import br.com.desafio_java.dto.AcompanhamentoResponseDto;
import br.com.desafio_java.dto.AcompanhamentoUpdateDto;
import br.com.desafio_java.enums.EnumStatusAcompanhamento;
import br.com.desafio_java.exception.RegistroNaoEncontradoException;
import br.com.desafio_java.mapper.AcompanhamentoMapper;
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
@RequestMapping("/acompanhamento")
@Api(description = "Acompanhamentos")
public class AcompanhamentoController {

    @Autowired
    private AcompanhamentoService _acompanhamentoService;

    @Autowired
    private AcompanhamentoMapper _acompanhamentoMapper;

    @ApiOperation(value = "Buscar todos os acompanhamentos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Acompanhamentos encontrados com sucesso")
    })
    @GetMapping("/acompanhamentos")
    public List<AcompanhamentoResponseDto> BuscarTodos() {
        List<Acompanhamento> listaAcompanhamentos = _acompanhamentoService.findAll();
        return _acompanhamentoMapper.toDtoResponse(listaAcompanhamentos);
    }

    @ApiOperation(value = "Cria um acompanhamento")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Acompanhamento criado com sucesso")
    })
    @PostMapping()
    public AcompanhamentoResponseDto IncluirAcompanhamento(@RequestBody AcompanhamentoPostDto acompanhamentoPostDto) {
        return _acompanhamentoMapper.toDtoResponse(_acompanhamentoService.IncluirAcompanhamento(acompanhamentoPostDto));
    }

    @ApiOperation(value = "Exclui um acompanhamento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Acompanhamento excluído com sucesso")
    })
    @DeleteMapping("/{id}")
    public void Deletar(@PathVariable Long id) {
        _acompanhamentoService.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Acompanhamento não encontrado"));
        _acompanhamentoService.deleteById(id);
    }

    @ApiOperation(value = "Atualiza um acompanhamento")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Acompanhamento atualizado com sucesso")
    })
    @PostMapping("/atualizar/{id}")
    public AcompanhamentoResponseDto AtualizarAcompanhamento(@RequestBody AcompanhamentoUpdateDto acompanhamentoUpdateDto) throws RegistroNaoEncontradoException {
        return _acompanhamentoMapper.toDtoResponse(_acompanhamentoService.AtualizarAcompanhamento(acompanhamentoUpdateDto));
    }

    @ApiOperation(value = "Busca um acompanhamento")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Acompanhamento encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public AcompanhamentoResponseDto BuscarPorId(@PathVariable Long id) throws RegistroNaoEncontradoException{
        Acompanhamento acompanhamento = _acompanhamentoService.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Acompanhamento não encontrado"));
        return _acompanhamentoMapper.toDtoResponse(acompanhamento);
    }

    @ApiOperation(value = "Busca os acompanhamentos por colaborador e status do acompanhamento")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Acompanhamentos encontrados com sucesso")
    })
    @GetMapping("/colaborador/{colaboradorId}")
    public List<AcompanhamentoResponseDto> BuscarAcompanhamentoPorColaboradorEStatus(@PathVariable Long colaboradorId, @RequestHeader EnumStatusAcompanhamento statusAcompanhamento) throws RegistroNaoEncontradoException{
        List<Acompanhamento> acompanhamentos = _acompanhamentoService.BuscarAcompanhamentoPorColaboradorEStatus(colaboradorId, statusAcompanhamento);
        return _acompanhamentoMapper.toDtoResponse(acompanhamentos);
    }

    @ApiOperation(value = "Busca os acompanhamentos por status do acompanhamento")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Acompanhamentos encontrados com sucesso")
    })
    @GetMapping("/status")
    public List<AcompanhamentoResponseDto> BuscarAcompanhamentoPorStatus(@RequestHeader EnumStatusAcompanhamento statusAcompanhamento) throws RegistroNaoEncontradoException{
        List<Acompanhamento> acompanhamentos = _acompanhamentoService.BuscarAcompanhamentoPorStatus(statusAcompanhamento);
        return _acompanhamentoMapper.toDtoResponse(acompanhamentos);
    }

    @ApiOperation(value = "Busca os acompanhamentos por ordem de serviço")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Acompanhamentos encontrados com sucesso")
    })
    @GetMapping("/ordemServico/{id}")
    public List<AcompanhamentoResponseDto> BuscarAcompanhamentoPorOrdemServico(@RequestHeader Long ordemServicoId) throws RegistroNaoEncontradoException{
        List<Acompanhamento> acompanhamentos = _acompanhamentoService.BuscarAcompanhamentoPorOrdemServico(ordemServicoId);
        return _acompanhamentoMapper.toDtoResponse(acompanhamentos);
    }
}
