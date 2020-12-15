package br.com.desafio_java.service;

import br.com.desafio_java.dto.AcompanhamentoPostDto;
import br.com.desafio_java.dto.AcompanhamentoUpdateDto;
import br.com.desafio_java.enums.EnumStatusAcompanhamento;
import br.com.desafio_java.exception.ErroSistemaException;
import br.com.desafio_java.exception.RegistroNaoEncontradoException;
import br.com.desafio_java.model.Acompanhamento;
import br.com.desafio_java.model.Colaborador;
import br.com.desafio_java.model.OrdemServico;
import br.com.desafio_java.repository.AcompanhamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcompanhamentoService extends ServiceAbstrataImpl<AcompanhamentoRepository, Acompanhamento, Long> {

    @Autowired
    public AcompanhamentoService(AcompanhamentoRepository repository) {
        super(repository);
    }

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private ColaboradorService colaboradorService;

    public Acompanhamento AtualizarAcompanhamento(AcompanhamentoUpdateDto acompanhamentoUpdateDto) {
        try {
            Acompanhamento acompanhamento = repository.findById(acompanhamentoUpdateDto.id).orElseThrow(() -> new RegistroNaoEncontradoException("Ordem de serviço não encontrada"));
            acompanhamento.descricao = acompanhamentoUpdateDto.descricao;
            acompanhamento.statusAcompanhamento = acompanhamentoUpdateDto.statusAcompanhamento;

            repository.save(acompanhamento);
            ordemServicoService.ValidarStatusOrdemServico(acompanhamento);

            return acompanhamento;
        }
        catch (Exception ex) {
            throw new ErroSistemaException("Não foi possível executar a rotina de atualização do acompanhamento");
        }
    }

    public Acompanhamento IncluirAcompanhamento(AcompanhamentoPostDto acompanhamentoPostDto) {
        try {
            Acompanhamento acompanhamento = new Acompanhamento();
            acompanhamento.descricao = acompanhamentoPostDto.descricao;
            acompanhamento.statusAcompanhamento = acompanhamentoPostDto.statusAcompanhamento;

            OrdemServico ordemServico = ordemServicoService.findById(acompanhamentoPostDto.ordemServicoId).orElseThrow(() -> new RegistroNaoEncontradoException("Ordem de serviço não encontrada"));
            Colaborador colaborador = colaboradorService.findById(acompanhamentoPostDto.colaboradorId).orElseThrow(() -> new RegistroNaoEncontradoException("Colaborador não encontrado"));
            acompanhamento.ordemServico = ordemServico;
            acompanhamento.colaborador = colaborador;
            acompanhamento.dataCriacao = LocalDateTime.now();

            ordemServicoService.ValidarStatusOrdemServico(acompanhamento);

            return repository.save(acompanhamento);
        }
        catch (Exception ex) {
            throw new ErroSistemaException("Não foi possível executar a rotina de criação do acompanhamento");
        }
    }

    public List<Acompanhamento> BuscarAcompanhamentoPorColaboradorEStatus(Long colaboradorId, EnumStatusAcompanhamento statusAcompanhamento) {
        try {
            Colaborador colaborador = colaboradorService.findById(colaboradorId).orElseThrow(() -> new RegistroNaoEncontradoException("Colaborador não encontrado"));

            List<Acompanhamento> acompanhamentos = repository.findAllByColaborador(colaborador)
                    .stream()
                    .filter(item -> item.statusAcompanhamento == statusAcompanhamento)
                    .collect(Collectors.toList());

            return acompanhamentos;
        }
        catch (Exception ex) {
            throw new ErroSistemaException("Não foi possível buscar os acompanhamentos");
        }
    }

    public List<Acompanhamento> BuscarAcompanhamentoPorStatus(EnumStatusAcompanhamento statusAcompanhamento) {
        try {
            return repository.findAllByStatusAcompanhamento(statusAcompanhamento);
        }
        catch (Exception ex) {
            throw new ErroSistemaException("Não foi possível buscar os acompanhamentos");
        }


    }

    public List<Acompanhamento> BuscarAcompanhamentoPorOrdemServico(Long ordemServicoId) {
        try {
            OrdemServico ordemServico = ordemServicoService.findById(ordemServicoId).orElseThrow(() -> new RegistroNaoEncontradoException("Ordem de serviço não encontrada"));

            List<Acompanhamento> acompanhamentos = repository.findAllByOrdemServicoOrderByDataCriacaoDesc(ordemServico);

            return acompanhamentos;
        }
        catch (Exception ex) {
            throw new ErroSistemaException("Não foi possível executar a rotina de atualização do acompanhamento");
        }
    }
}
