package br.com.desafio_java.controller;

import br.com.desafio_java.model.Equipamento;
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
public class EquipamentoControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void verificarEquipamentoCadastrado_InformaIdEquipamentoCadastrado_EsperaMarcaCorreta() throws Exception {
        mvc.perform(get("/equipamento/{id}", cadastrarEquipamento().id)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.marca", is("Samsung")));
    }

    @Test
    public void deleteEquipamento_InformaIdEquipamento_EsperaStatusSucesso() throws Exception {

        mvc.perform(delete("/equipamento/{id}", cadastrarEquipamento().id)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void atualizarEquipamento_InformaEquipamento_EsperaMarcaAtualizada() throws Exception {
        Equipamento equipamento = cadastrarEquipamento();
        equipamento.marca = "AOC";

        mvc.perform(post("/equipamento/atualizar/{id}", equipamento.id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(convertObjectToJsonBytes(equipamento)))
                .andExpect(jsonPath("$.marca", is("AOC")));
    }


}
