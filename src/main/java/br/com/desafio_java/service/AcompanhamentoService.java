package br.com.desafio_java.service;

import br.com.desafio_java.dto.AcompanhamentoPostDto;
import br.com.desafio_java.dto.AcompanhamentoUpdateDto;
import br.com.desafio_java.enums.EnumStatusAcompanhamento;
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
    private OrdemServicoService _ordemServicoService;

    @Autowired
    private ColaboradorService _colaboradorService;

    public Acompanhamento AtualizarAcompanhamento(AcompanhamentoUpdateDto acompanhamentoUpdateDto) {
        Acompanhamento acompanhamento = repository.findById(acompanhamentoUpdateDto.id).orElseThrow(() -> new RegistroNaoEncontradoException("Ordem de serviço não encontrada"));
        acompanhamento.descricao = acompanhamentoUpdateDto.descricao;
        acompanhamento.statusAcompanhamento = acompanhamentoUpdateDto.statusAcompanhamento;
        acompanhamento.dataAtualizacao = LocalDateTime.now();

        repository.save(acompanhamento);
        _ordemServicoService.ValidarStatusOrdemServico(acompanhamento);

        return acompanhamento;
    }

    public Acompanhamento IncluirAcompanhamento(AcompanhamentoPostDto acompanhamentoPostDto) {
        Acompanhamento acompanhamento = new Acompanhamento();
        acompanhamento.descricao = acompanhamentoPostDto.descricao;
        acompanhamento.statusAcompanhamento = acompanhamentoPostDto.statusAcompanhamento;

        OrdemServico ordemServico = _ordemServicoService.findById(acompanhamentoPostDto.ordemServicoId).orElseThrow(() -> new RegistroNaoEncontradoException("Ordem de serviço não encontrada"));
        Colaborador colaborador = _colaboradorService.findById(acompanhamentoPostDto.colaboradorId).orElseThrow(() -> new RegistroNaoEncontradoException("Colaborador não encontrado"));
        acompanhamento.ordemServico = ordemServico;
        acompanhamento.colaborador = colaborador;
        acompanhamento.dataCriacao = LocalDateTime.now();

        _ordemServicoService.ValidarStatusOrdemServico(acompanhamento);

        return repository.save(acompanhamento);
    }

    public List<Acompanhamento> BuscarAcompanhamentoPorColaboradorEStatus(Long colaboradorId, EnumStatusAcompanhamento statusAcompanhamento) {
        Colaborador colaborador = _colaboradorService.findById(colaboradorId).orElseThrow(() -> new RegistroNaoEncontradoException("Colaborador não encontrado"));

        List<Acompanhamento> acompanhamentos = repository.findAllByColaborador(colaborador)
                .stream()
                .filter(item -> item.statusAcompanhamento == statusAcompanhamento)
                .collect(Collectors.toList());

        return acompanhamentos;
    }

    public List<Acompanhamento> BuscarAcompanhamentoPorStatus(EnumStatusAcompanhamento statusAcompanhamento) {
        return repository.findAllByStatusAcompanhamento(statusAcompanhamento);
    }

    public List<Acompanhamento> BuscarAcompanhamentoPorOrdemServico(Long ordemServicoId) {
        OrdemServico ordemServico = _ordemServicoService.findById(ordemServicoId).orElseThrow(() -> new RegistroNaoEncontradoException("Ordem de serviço não encontrada"));

        List<Acompanhamento> acompanhamentos = repository.findAllByOrdemServicoOrderByDataCriacaoDesc(ordemServico);

        return acompanhamentos;
    }
}
