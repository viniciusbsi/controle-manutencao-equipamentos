package br.com.desafio_java.dto;

import br.com.desafio_java.enums.EnumStatusAcompanhamento;
import br.com.desafio_java.model.Equipamento;

import java.time.LocalDateTime;

public class AcompanhamentoResponseDto {

    public Long id;

    public EnumStatusAcompanhamento statusAcompanhamento;

    public String descricao;

    public LocalDateTime dataCriacao;

    public String nomeColaborador;

    public Long ordemServicoId;

    public Long colaboradorId;

    public Equipamento equipamento;
}
