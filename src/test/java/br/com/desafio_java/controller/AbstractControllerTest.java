package br.com.desafio_java.controller;

import br.com.desafio_java.dto.*;
import br.com.desafio_java.enums.EnumSetor;
import br.com.desafio_java.enums.EnumStatusAcompanhamento;
import br.com.desafio_java.model.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    protected Equipamento CadastrarEquipamento() throws Exception {

        EquipamentoPostDto equipamento = new EquipamentoPostDto();
        equipamento.marca = "Samsung";
        equipamento.nome = "TV 32";
        equipamento.tipo = "TV";

        String equipamentoRetorno = mvc.perform(post("/equipamento")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(ConverterObjetoParaJsonBytes(equipamento)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject json = new JSONObject(equipamentoRetorno);
        Equipamento equipamentoCadastrado = new Equipamento();
        equipamentoCadastrado.id = Long.valueOf((Integer) json.get("id"));
        equipamentoCadastrado.marca = (String) json.get("marca");
        equipamentoCadastrado.tipo = (String) json.get("tipo");
        equipamentoCadastrado.nome = (String) json.get("nome");

        return equipamentoCadastrado;
    }

    protected Cliente CadastrarCliente() throws Exception {

        ClientePostDto cliente = new ClientePostDto();
        cliente.nome = "João";
        cliente.email = "teste@teste.com";
        cliente.endereco = "Rua X, 10";
        cliente.telefone = "(47) 99999-9999";

        String clienteRetorno = mvc.perform(post("/cliente")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(ConverterObjetoParaJsonBytes(cliente)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject json = new JSONObject(clienteRetorno);
        Cliente clienteCadastrado = new Cliente();
        clienteCadastrado.id = Long.valueOf((Integer) json.get("id"));
        clienteCadastrado.nome = (String) json.get("nome");
        clienteCadastrado.email = (String) json.get("email");
        clienteCadastrado.endereco = (String) json.get("endereco");
        clienteCadastrado.telefone = (String) json.get("telefone");

        return clienteCadastrado;
    }

    protected Colaborador CadastrarColaborador() throws Exception {

        ColaboradorPostDto colaborador = new ColaboradorPostDto();
        colaborador.nome = "João";
        colaborador.email = "teste@teste.com";
        colaborador.endereco = "Rua X, 10";
        colaborador.telefone = "(47) 99999-9999";
        colaborador.setor = EnumSetor.MANUTENCAO;

        String colaboradorRetorno = mvc.perform(post("/colaborador")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(ConverterObjetoParaJsonBytes(colaborador)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject json = new JSONObject(colaboradorRetorno);
        Colaborador colaboradorCadastrado = new Colaborador();
        colaboradorCadastrado.id = Long.valueOf((Integer) json.get("id"));
        colaboradorCadastrado.nome = (String) json.get("nome");
        colaboradorCadastrado.email = (String) json.get("email");
        colaboradorCadastrado.endereco = (String) json.get("endereco");
        colaboradorCadastrado.telefone = (String) json.get("telefone");

        return colaboradorCadastrado;
    }

    protected OrdemServico CadastrarOrdemServico() throws Exception {

        Equipamento equipamento = CadastrarEquipamento();
        Colaborador colaborador = CadastrarColaborador();
        Cliente cliente = CadastrarCliente();

        OrdemServicoPostDto ordemServicoPostDto = new OrdemServicoPostDto();
        ordemServicoPostDto.clienteId = cliente.id;
        ordemServicoPostDto.colaboradorId = colaborador.id;
        ordemServicoPostDto.equipamentoId = equipamento.id;
        ordemServicoPostDto.descricao = "Trocar botões de volume e controle remoto";

        String ordemServicoRetorno = mvc.perform(post("/ordemServico")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(ConverterObjetoParaJsonBytes(ordemServicoPostDto)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject json = new JSONObject(ordemServicoRetorno);
        OrdemServico ordemServico = new OrdemServico();
        ordemServico.id = Long.valueOf((Integer) json.get("id"));
        ordemServico.descricao = (String) json.get("descricao");
        return ordemServico;
    }

    protected Acompanhamento CadastrarAcompanhamneto() throws Exception {

        OrdemServico ordemServico = CadastrarOrdemServico();
        Colaborador colaborador = CadastrarColaborador();

        AcompanhamentoPostDto acompanhamentoPostDto = new AcompanhamentoPostDto();
        acompanhamentoPostDto.ordemServicoId = ordemServico.id;
        acompanhamentoPostDto.colaboradorId = colaborador.id;
        acompanhamentoPostDto.descricao = "descrição acompanhamento";
        acompanhamentoPostDto.statusAcompanhamento = EnumStatusAcompanhamento.PENDENTE;

        String acompanhamentoRetorno = mvc.perform(post("/acompanhamento")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(ConverterObjetoParaJsonBytes(acompanhamentoPostDto)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject json = new JSONObject(acompanhamentoRetorno);
        Acompanhamento acompanhamento = new Acompanhamento();
        acompanhamento.id = Long.valueOf((Integer) json.get("id"));
        acompanhamento.descricao = (String) json.get("descricao");
        return acompanhamento;
    }

    protected static byte[] ConverterObjetoParaJsonBytes(Object object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.writeValueAsBytes(object);
    }
}
