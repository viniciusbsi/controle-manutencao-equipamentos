package br.com.desafio_java.enums;

public enum EnumStatusAcompanhamento {

    PENDENTE("Pendente"),
    EM_ANDAMENTO("Em andamento"),
    INTERROMPIDO("Interrompido"),
    FINALIZADO("Finalizado");

    private String descricao;

    EnumStatusAcompanhamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
