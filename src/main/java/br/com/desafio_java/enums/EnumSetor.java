package br.com.desafio_java.enums;

public enum EnumSetor {

    MANUTENCAO("Manutenção"),
    ATENDIMENTO("Atendimento");

    private String descricao;

    EnumSetor(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
