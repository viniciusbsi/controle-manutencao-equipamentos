package br.com.desafio_java.model;

import javax.persistence.*;

@Entity
@Table(name = "equipamento")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "nome")
    public String nome;

    @Column(name = "tipo")
    public String tipo;

    @Column(name = "marca")
    public String marca;
}
