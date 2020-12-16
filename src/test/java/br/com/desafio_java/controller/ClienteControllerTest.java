package br.com.desafio_java.controller;

import br.com.desafio_java.model.Cliente;
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
public class ClienteControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void verificarClienteCadastrado_InformaIdClienteCadastrado_EsperaNomeCorreto() throws Exception {
        mvc.perform(get("/cliente/{id}", CadastrarCliente().id)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.nome", is("João")));
    }

    @Test
    public void deleteCliente_InformaIdCliente_EsperaStatusSucesso() throws Exception {

        mvc.perform(delete("/cliente/{id}", CadastrarCliente().id)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void atualizarEquipamento_InformaEquipamento_EsperaMarcaAtualizada() throws Exception {
        Cliente cliente = CadastrarCliente();
        cliente.nome = "João nome editado";

        mvc.perform(post("/cliente/atualizar/{id}", cliente.id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(ConverterObjetoParaJsonBytes(cliente)))
                .andExpect(jsonPath("$.nome", is("João nome editado")));
    }


}
