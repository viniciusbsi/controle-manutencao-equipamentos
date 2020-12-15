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
@RequestMapping("/Colaborador")
@Api(description = "Colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @Autowired
    private ColaboradorMapper colaboradorMapper;

    @ApiOperation(value = "Buscar todos os Colaboradores")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Colaboradores encontrados com sucesso")
    })
    @GetMapping("/colaboradores")
    public List<Colaborador> BuscarTodos() {
        try {
            List<Colaborador> listaColaborador = colaboradorService.findAll();
            return listaColaborador;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @ApiOperation(value = "Cria um Colaborador")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Colaborador criado com sucesso")
    })
    @PostMapping("/colaborador")
    public Colaborador IncluirColaborador(@RequestBody ColaboradorPostDto colaboradorPostDto) {
        Colaborador colaborador = colaboradorMapper.toEntity(colaboradorPostDto);
        colaboradorService.save(colaborador);
        return colaborador;
    }

    @ApiOperation(value = "Exclui um colaborador")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Colaborador excluído com sucesso")
    })
    @DeleteMapping("/{id}")
    public void Deletar(@PathVariable Long id) {
        colaboradorService.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Colaborador não encontrado"));
        colaboradorService.deleteById(id);
    }

    @ApiOperation(value = "Atualiza um colaborador")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = " Colaborador atualizado com sucesso")
    })
    @PostMapping("/atualizar/{id}")
    public Colaborador UpdateColaborador(@RequestBody  Colaborador colaborador) throws RegistroNaoEncontradoException {
        colaboradorService.findById(colaborador.id).orElseThrow(() -> new RegistroNaoEncontradoException("Colaborador não encontrado"));
         Colaborador colaboradorAtualizado = colaboradorService.save(colaborador);
        return colaboradorAtualizado;
    }

    @ApiOperation(value = "Busca um colaborador")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Colaborador encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public  Colaborador BuscarPorId(@PathVariable Long id) throws RegistroNaoEncontradoException{
         Colaborador colaborador = colaboradorService.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Colaborador não encontrado"));
        return colaborador;
    }
}
