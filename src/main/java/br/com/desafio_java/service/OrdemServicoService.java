package br.com.desafio_java.service;

import br.com.desafio_java.dto.OrdemServicoGetDto;
import br.com.desafio_java.dto.OrdemServicoPostDto;
import br.com.desafio_java.dto.OrdemServicoUpdateDto;
import br.com.desafio_java.enums.EnumStatusAcompanhamento;
import br.com.desafio_java.exception.ErroFinalizacaoOrdemServicoException;
import br.com.desafio_java.exception.RegistroNaoEncontradoException;
import br.com.desafio_java.mapper.OrdemServicoMapper;
import br.com.desafio_java.model.*;
import br.com.desafio_java.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrdemServicoService extends ServiceAbstrataImpl<OrdemServicoRepository, OrdemServico, Long> {

    @Autowired
    public OrdemServicoService(OrdemServicoRepository repository) {
        super(repository);
    }

    @Autowired
    private EquipamentoService _equipamentoService;

    @Autowired
    private ColaboradorService _colaboradorService;

    @Autowired
    private ClienteService _clienteService;

    @Autowired
    private AcompanhamentoService _acompanhamentoService;

    @Autowired
    private OrdemServicoMapper _ordemServicoMapper;

    public OrdemServico AtualizarOrdemServico(OrdemServicoUpdateDto ordemServicoUpdateDto) {
        OrdemServico ordemServico = repository.findById(ordemServicoUpdateDto.id).orElseThrow(() -> new RegistroNaoEncontradoException("Ordem de serviço não encontrada"));
        ordemServico.descricao = ordemServicoUpdateDto.descricao;
        ordemServico.dataAtualizacao = LocalDateTime.now();
        repository.save(ordemServico);
        return ordemServico;
    }

    public OrdemServicoGetDto BuscarDadosOrdemServico(Long ordemServicoId) {
        OrdemServico ordemServico = repository.findById(ordemServicoId).orElseThrow(() -> new RegistroNaoEncontradoException("Ordem de serviço não encontrada"));
        Acompanhamento acompanhamento = _acompanhamentoService.repository.findAllByOrdemServicoOrderByDataCriacaoDesc(ordemServico).iterator().next();

        return _ordemServicoMapper.toDtoDadosOrdemServicoEAcompanhamento(ordemServico, acompanhamento);
    }

    public OrdemServico IncluirOrdemServico(OrdemServicoPostDto ordemServicoPostDto) {
        OrdemServico ordemServico = new OrdemServico();
        ordemServico.descricao = ordemServicoPostDto.descricao;

        Equipamento equipamento = _equipamentoService.findById(ordemServicoPostDto.equipamentoId).orElseThrow(() -> new RegistroNaoEncontradoException("Equipamento não encontrado"));
        Colaborador colaborador = _colaboradorService.findById(ordemServicoPostDto.colaboradorId).orElseThrow(() -> new RegistroNaoEncontradoException("Colaborador não encontrado"));
        Cliente cliente = _clienteService.findById(ordemServicoPostDto.clienteId).orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));

        ordemServico.equipamento = equipamento;
        ordemServico.colaborador = colaborador;
        ordemServico.cliente = cliente;
        ordemServico.dataCriacao = LocalDateTime.now();

        return repository.save(ordemServico);
    }

    public void ValidarStatusOrdemServico(Acompanhamento acompanhamento) {
        if (acompanhamento.statusAcompanhamento == EnumStatusAcompanhamento.FINALIZADO) {
            FinalizarOrdemServico(acompanhamento.ordemServico.id);
        } else if (acompanhamento.statusAcompanhamento == EnumStatusAcompanhamento.EM_ANDAMENTO || acompanhamento.statusAcompanhamento == EnumStatusAcompanhamento.INTERROMPIDO) {
            IniciarOrdemServico(acompanhamento.ordemServico.id);
        }
    }

    public OrdemServico FinalizarOrdemServico(Long ordemServicoId) {
        OrdemServico ordemServico = repository.findById(ordemServicoId).orElseThrow(() -> new RegistroNaoEncontradoException("Ordem de serviço não encontrada"));

        if (ordemServico.dataInicio == null)
            throw new ErroFinalizacaoOrdemServicoException("A ordem de serviço ainda não foi iniciada");

        if (ordemServico.dataFim == null)
            ordemServico.dataFim = LocalDateTime.now();

        repository.save(ordemServico);
        return ordemServico;
    }

    public OrdemServico IniciarOrdemServico(Long ordemServicoId) {
        OrdemServico ordemServico = repository.findById(ordemServicoId).orElseThrow(() -> new RegistroNaoEncontradoException("Ordem de serviço não encontrada"));
        if (ordemServico.dataInicio == null)
            ordemServico.dataInicio = LocalDateTime.now();
        repository.save(ordemServico);
        return ordemServico;
    }
}
