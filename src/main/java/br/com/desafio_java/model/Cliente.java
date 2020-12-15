package br.com.desafio_java.model;
import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

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
}
