package br.com.desafio_java.controller;

import br.com.desafio_java.dto.OrdemServicoGetDto;
import br.com.desafio_java.dto.OrdemServicoPostDto;
import br.com.desafio_java.dto.OrdemServicoUpdateDto;
import br.com.desafio_java.exception.RegistroNaoEncontradoException;
import br.com.desafio_java.model.OrdemServico;
import br.com.desafio_java.service.OrdemServicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordemServico")
@Api(description = "OrdemServicos")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService _ordemServicoService;

    @ApiOperation(value = "Buscar todos as orderns de serviços")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Ordens de serviços encontradas com sucesso")
    })
    @GetMapping("/ordemServicos")
    public List<OrdemServico> BuscarTodos() {
        List<OrdemServico> listaOrdemServicos = _ordemServicoService.findAll();
        return listaOrdemServicos;
    }

    @ApiOperation(value = "Buscar dados de uma ordem de serviço")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Ordem de serviço encontrada com sucesso")
    })
    @GetMapping("/ordemServicos/{id}")
    public OrdemServicoGetDto BuscarDadosOrdemServico(@PathVariable(value = "id") Long ordemServicoId) {
        return _ordemServicoService.BuscarDadosOrdemServico(ordemServicoId);
    }

    @ApiOperation(value = "Cria uma order de serviço")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Order de serviço criada com sucesso")
    })
    @PostMapping()
    public OrdemServico IncluirOrdemServico(@RequestBody OrdemServicoPostDto ordemServicoPostDto) {
        return _ordemServicoService.IncluirOrdemServico(ordemServicoPostDto);
    }

    @ApiOperation(value = "Exclui uma ordem de serviço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ordem de serviço excluída com sucesso")
    })
    @DeleteMapping("/{id}")
    public void Deletar(@PathVariable Long id) {
        _ordemServicoService.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Colaborador não encontrado"));
        _ordemServicoService.deleteById(id);
    }

    @ApiOperation(value = "Atualiza uma ordem de serviço")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Ordem de serviço atualizada com sucesso")
    })
    @PostMapping("/atualizar/{id}")
    public OrdemServico AtualizarOrdemServico(@RequestBody OrdemServicoUpdateDto ordemServicoUpdateDto) throws RegistroNaoEncontradoException {
        return _ordemServicoService.AtualizarOrdemServico(ordemServicoUpdateDto);
    }

    @ApiOperation(value = "Busca uma ordem de serviço")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Ordem de serviço encontrada com sucesso")
    })
    @GetMapping("/{id}")
    public OrdemServico BuscarPorId(@PathVariable Long id) throws RegistroNaoEncontradoException{
        OrdemServico ordemServico = _ordemServicoService.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Ordem de serviço não encontrada"));
        return ordemServico;
    }
}
