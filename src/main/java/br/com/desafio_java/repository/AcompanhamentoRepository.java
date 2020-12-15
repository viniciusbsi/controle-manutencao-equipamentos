package br.com.desafio_java.repository;

import br.com.desafio_java.enums.EnumStatusAcompanhamento;
import br.com.desafio_java.model.Acompanhamento;
import br.com.desafio_java.model.Colaborador;
import br.com.desafio_java.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcompanhamentoRepository extends JpaRepository<Acompanhamento, Long> {

    List<Acompanhamento> findAllByStatusAcompanhamento(EnumStatusAcompanhamento statusAcompanhamento);

    List<Acompanhamento> findAllByOrdemServicoOrderByDataCriacaoDesc(OrdemServico ordemServico);

    List<Acompanhamento> findAllByColaborador(Colaborador colaborador);
}
