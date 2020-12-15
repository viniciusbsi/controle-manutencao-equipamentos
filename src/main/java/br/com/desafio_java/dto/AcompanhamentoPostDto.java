package br.com.desafio_java.dto;

import br.com.desafio_java.enums.EnumStatusAcompanhamento;

public class AcompanhamentoPostDto {

    public EnumStatusAcompanhamento statusAcompanhamento;

    public String descricao;

    public Long ordemServicoId;

    public Long colaboradorId;
}
