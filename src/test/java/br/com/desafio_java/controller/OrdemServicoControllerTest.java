package br.com.desafio_java.controller;

import br.com.desafio_java.model.OrdemServico;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrdemServicoControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void verificarOrdemServicoCadastrada_InformaIdOrdemServicoCadastrada_EsperaDescricaoCorreta() throws Exception {
        mvc.perform(get("/ordemServico/{id}", CadastrarOrdemServico().id)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.descricao", is("Trocar botões de volume e controle remoto")));
    }

    @Test
    public void deleteOrdemServico_InformaIdOrdemServico_EsperaStatusSucesso() throws Exception {

        mvc.perform(delete("/ordemServico/{id}", CadastrarOrdemServico().id)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void atualizarOrdemServico_InformaOrdemServico_EsperaDescricaoAtualizada() throws Exception {
        OrdemServico ordemServico = CadastrarOrdemServico();
        ordemServico.descricao = "Descrição editada";

        mvc.perform(post("/ordemServico/atualizar/{id}", ordemServico.id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(ConverterObjetoParaJsonBytes(ordemServico)))
                .andExpect(jsonPath("$.descricao", is("Descrição editada")));
    }


}
