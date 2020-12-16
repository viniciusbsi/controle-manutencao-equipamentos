package br.com.desafio_java.controller;

import br.com.desafio_java.dto.ColaboradorPostDto;
import br.com.desafio_java.exception.RegistroNaoEncontradoException;
import br.com.desafio_java.mapper.ColaboradorMapper;
import br.com.desafio_java.model.Colaborador;
import br.com.desafio_java.service.ColaboradorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colaborador")
@Api(description = "Colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService _colaboradorService;

    @Autowired
    private ColaboradorMapper _colaboradorMapper;

    @ApiOperation(value = "Buscar todos os Colaboradores")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Colaboradores encontrados com sucesso")
    })
    @GetMapping("/colaboradores")
    public List<Colaborador> BuscarTodos() {
        try {
            List<Colaborador> listaColaborador = _colaboradorService.findAll();
            return listaColaborador;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @ApiOperation(value = "Cria um Colaborador")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Colaborador criado com sucesso")
    })
    @PostMapping()
    public Colaborador IncluirColaborador(@RequestBody ColaboradorPostDto colaboradorPostDto) {
        Colaborador colaborador = _colaboradorMapper.toEntity(colaboradorPostDto);
        _colaboradorService.save(colaborador);
        return colaborador;
    }

    @ApiOperation(value = "Exclui um colaborador")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Colaborador excluído com sucesso")
    })
    @DeleteMapping("/{id}")
    public void Deletar(@PathVariable Long id) {
        _colaboradorService.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Colaborador não encontrado"));
        _colaboradorService.deleteById(id);
    }

    @ApiOperation(value = "Atualiza um colaborador")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = " Colaborador atualizado com sucesso")
    })
    @PostMapping("/atualizar/{id}")
    public Colaborador AtualizarColaborador(@RequestBody  Colaborador colaborador) throws RegistroNaoEncontradoException {
        _colaboradorService.findById(colaborador.id).orElseThrow(() -> new RegistroNaoEncontradoException("Colaborador não encontrado"));
         Colaborador colaboradorAtualizado = _colaboradorService.save(colaborador);
        return colaboradorAtualizado;
    }

    @ApiOperation(value = "Busca um colaborador")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Colaborador encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public  Colaborador BuscarPorId(@PathVariable Long id) throws RegistroNaoEncontradoException{
         Colaborador colaborador = _colaboradorService.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Colaborador não encontrado"));
        return colaborador;
    }
}
