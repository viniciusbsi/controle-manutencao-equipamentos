package br.com.desafio_java.service;

import br.com.desafio_java.dto.OrdemServicoGetDto;
import br.com.desafio_java.dto.OrdemServicoPostDto;
import br.com.desafio_java.dto.OrdemServicoUpdateDto;
import br.com.desafio_java.enums.EnumStatusAcompanhamento;
import br.com.desafio_java.exception.ErroSistemaException;
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
    private EquipamentoService equipamentoService;

    @Autowired
    private ColaboradorService colaboradorService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AcompanhamentoService acompanhamentoService;

    @Autowired
    private OrdemServicoMapper ordemServicoMapper;

    public OrdemServico AtualizarOrdemServico(OrdemServicoUpdateDto ordemServicoUpdateDto) {
        try {
            OrdemServico ordemServico = repository.findById(ordemServicoUpdateDto.id).orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada"));
            ordemServico.descricao = ordemServicoUpdateDto.descricao;
            repository.save(ordemServico);
            return ordemServico;
        }
        catch (Exception exception) {
            throw new ErroSistemaException("Não foi possível executar a rotina de atualização da ordem de serviço");
        }


    }

    public OrdemServicoGetDto BuscarDadosOrdemServico(Long ordemServicoId) {
        try {
            OrdemServico ordemServico = repository.findById(ordemServicoId).orElseThrow(() -> new RegistroNaoEncontradoException("Ordem de serviço não encontrada"));
            Acompanhamento acompanhamento = acompanhamentoService.repository.findAllByOrdemServicoOrderByDataCriacaoDesc(ordemServico).iterator().next();

            return ordemServicoMapper.toDtoDadosOrdemServicoEAcompanhamento(ordemServico, acompanhamento);
        }
        catch (Exception exception) {
            throw new ErroSistemaException("Não foi possível realizar a busca da ordem de serviço");
        }
    }

    public OrdemServico IncluirOrdemServico(OrdemServicoPostDto ordemServicoPostDto) {
        try {
            OrdemServico ordemServico = new OrdemServico();
            ordemServico.descricao = ordemServicoPostDto.descricao;

            Equipamento equipamento = equipamentoService.findById(ordemServicoPostDto.equipamentoId).orElseThrow(() -> new RegistroNaoEncontradoException("Equipamento não encontrado"));
            Colaborador colaborador = colaboradorService.findById(ordemServicoPostDto.colaboradorId).orElseThrow(() -> new RegistroNaoEncontradoException("Colaborador não encontrado"));
            Cliente cliente = clienteService.findById(ordemServicoPostDto.clienteId).orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));

            ordemServico.equipamento = equipamento;
            ordemServico.colaborador = colaborador;
            ordemServico.cliente = cliente;
            ordemServico.dataCriacao = LocalDateTime.now();

            return repository.save(ordemServico);
        }
        catch (Exception exception) {
            throw new ErroSistemaException("Não foi possível executar a rotina de inclusão da ordem de serviço");
        }
    }

    public void ValidarStatusOrdemServico(Acompanhamento acompanhamento) {
        try {
            if (acompanhamento.statusAcompanhamento == EnumStatusAcompanhamento.FINALIZADO) {
                FinalizarOrdemServico(acompanhamento.ordemServico.id);
            } else if (acompanhamento.statusAcompanhamento == EnumStatusAcompanhamento.EM_ANDAMENTO) {
                IniciarOrdemServico(acompanhamento.ordemServico.id);
            }
        }
        catch (Exception exception) {
            throw new ErroSistemaException("Não foi possível executar a rotina de validação do status da ordem de serviço");
        }
    }

    public OrdemServico FinalizarOrdemServico(Long ordemServicoId) {
        OrdemServico ordemServico = repository.findById(ordemServicoId).orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada"));
        if (ordemServico.dataFim == null)
            ordemServico.dataFim = LocalDateTime.now();
        repository.save(ordemServico);
        return ordemServico;
    }

    public OrdemServico IniciarOrdemServico(Long ordemServicoId) {
        OrdemServico ordemServico = repository.findById(ordemServicoId).orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada"));
        if (ordemServico.dataInicio == null)
            ordemServico.dataInicio = LocalDateTime.now();
        repository.save(ordemServico);
        return ordemServico;
    }
}
