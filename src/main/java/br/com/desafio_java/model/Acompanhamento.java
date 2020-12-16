package br.com.desafio_java.model;

import br.com.desafio_java.enums.EnumStatusAcompanhamento;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "acompanhamento")
public class Acompanhamento extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "statusAcompanhamento")
    @Enumerated(EnumType.STRING)
    public EnumStatusAcompanhamento statusAcompanhamento;

    @Column(name = "descricao")
    public String descricao;

    @ManyToOne
    public OrdemServico ordemServico;

    @ManyToOne
    public Colaborador colaborador;
}
