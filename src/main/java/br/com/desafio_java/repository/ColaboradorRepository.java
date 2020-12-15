package br.com.desafio_java.repository;

import br.com.desafio_java.model.Cliente;
import br.com.desafio_java.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

}
