package br.com.desafio_java.model;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordem_servico")
public class OrdemServico extends EntidadeAuditavel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "descricao")
    public String descricao;

    @Column(name = "dataInicio")
    public LocalDateTime dataInicio;

    @Column(name = "dataFim")
    public LocalDateTime dataFim;

    @ManyToOne
    public Cliente cliente;

    @ManyToOne
    public Colaborador colaborador;

    @ManyToOne
    public Equipamento equipamento;
}
