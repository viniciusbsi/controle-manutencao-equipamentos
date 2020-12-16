package br.com.desafio_java.dto;

import br.com.desafio_java.enums.EnumStatusAcompanhamento;

import java.time.LocalDateTime;

public class OrdemServicoGetDto {

    public String descricao;
    public String nomeCliente;
    public String nomeResponsavel;
    public String descricaoAcompanhamento;
    public String colaboradorAcompanhamento;
    public EnumStatusAcompanhamento statusAcompanhamento;
    public LocalDateTime dataInicio;
    public LocalDateTime dataFim;
    public LocalDateTime dataAtualizacaoAcompanhamento;
}
