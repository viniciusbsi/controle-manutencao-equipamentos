package br.com.desafio_java.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=true)
@MappedSuperclass
public class EntidadeAuditavel {

    @Column(name = "dataCriacao")
    public LocalDateTime dataCriacao;

    @Column(name = "dataAtualizacao")
    public LocalDateTime dataAtualizacao;
}
