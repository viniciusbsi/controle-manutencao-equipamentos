package br.com.desafio_java.model;

import br.com.desafio_java.enums.EnumSetor;

import javax.persistence.*;

@Entity
@Table(name = "colaborador")
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "nome")
    public String nome;

    @Column(name = "endereco")
    public String endereco;

    @Column(name = "telefone")
    public String telefone;

    @Column(name = "email")
    public String email;

    @Column(name = "setor")
    @Enumerated(EnumType.STRING)
    public EnumSetor setor;


}
