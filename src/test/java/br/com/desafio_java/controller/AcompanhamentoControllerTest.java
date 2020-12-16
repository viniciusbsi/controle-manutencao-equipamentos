package br.com.desafio_java.controller;

import br.com.desafio_java.model.Acompanhamento;
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
public class AcompanhamentoControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void verificarAcompanhamentoCadastrado_InformaIdAcompanhamentoCadastrado_EsperaDescricaoCorreta() throws Exception {
        mvc.perform(get("/acompanhamento/{id}", CadastrarAcompanhamneto().id)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.descricao", is("descrição acompanhamento")));
    }

    @Test
    public void deleteAcompanhamento_InformaIdAcompanhamento_EsperaStatusSucesso() throws Exception {

        mvc.perform(delete("/acompanhamento/{id}", CadastrarAcompanhamneto().id)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void atualizarAcompanhamento_InformaAcompanhamento_EsperaDescricaoAtualizada() throws Exception {
        Acompanhamento acompanhamento = CadastrarAcompanhamneto();
        acompanhamento.descricao = "Descrição editada";

        mvc.perform(post("/acompanhamento/atualizar/{id}", acompanhamento.id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(ConverterObjetoParaJsonBytes(acompanhamento)))
                .andExpect(jsonPath("$.descricao", is("Descrição editada")));
    }


}
