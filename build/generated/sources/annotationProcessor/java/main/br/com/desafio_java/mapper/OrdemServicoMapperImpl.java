package br.com.desafio_java.mapper;

import br.com.desafio_java.dto.OrdemServicoGetDto;
import br.com.desafio_java.model.Acompanhamento;
import br.com.desafio_java.model.Cliente;
import br.com.desafio_java.model.Colaborador;
import br.com.desafio_java.model.OrdemServico;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-16T15:04:55-0300",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class OrdemServicoMapperImpl implements OrdemServicoMapper {

    @Override
    public List<OrdemServicoGetDto> toDTO(List<OrdemServico> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<OrdemServicoGetDto> list = new ArrayList<OrdemServicoGetDto>( dtos.size() );
        for ( OrdemServico ordemServico : dtos ) {
            list.add( toDTO( ordemServico ) );
        }

        return list;
    }

    @Override
    public List<OrdemServico> toEntity(List<OrdemServicoGetDto> entities) {
        if ( entities == null ) {
            return null;
        }

        List<OrdemServico> list = new ArrayList<OrdemServico>( entities.size() );
        for ( OrdemServicoGetDto ordemServicoGetDto : entities ) {
            list.add( toEntity( ordemServicoGetDto ) );
        }

        return list;
    }

    @Override
    public OrdemServicoGetDto toDTO(OrdemServico entity) {
        if ( entity == null ) {
            return null;
        }

        OrdemServicoGetDto ordemServicoGetDto = new OrdemServicoGetDto();

        ordemServicoGetDto.descricao = entity.descricao;
        ordemServicoGetDto.dataInicio = entity.dataInicio;
        ordemServicoGetDto.dataFim = entity.dataFim;

        return ordemServicoGetDto;
    }

    @Override
    public OrdemServico toEntity(OrdemServicoGetDto usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        OrdemServico ordemServico = new OrdemServico();

        ordemServico.descricao = usuarioDTO.descricao;
        ordemServico.dataInicio = usuarioDTO.dataInicio;
        ordemServico.dataFim = usuarioDTO.dataFim;

        return ordemServico;
    }

    @Override
    public OrdemServicoGetDto toDtoDadosOrdemServicoEAcompanhamento(OrdemServico ordemServico, Acompanhamento acompanhamento) {
        if ( ordemServico == null && acompanhamento == null ) {
            return null;
        }

        OrdemServicoGetDto ordemServicoGetDto = new OrdemServicoGetDto();

        if ( ordemServico != null ) {
            ordemServicoGetDto.descricao = ordemServico.descricao;
            ordemServicoGetDto.nomeCliente = ordemServicoClienteNome( ordemServico );
            ordemServicoGetDto.nomeResponsavel = ordemServicoColaboradorNome( ordemServico );
            ordemServicoGetDto.dataInicio = ordemServico.dataInicio;
            ordemServicoGetDto.dataFim = ordemServico.dataFim;
        }
        if ( acompanhamento != null ) {
            ordemServicoGetDto.colaboradorAcompanhamento = acompanhamentoColaboradorNome( acompanhamento );
            ordemServicoGetDto.descricaoAcompanhamento = acompanhamento.descricao;
            ordemServicoGetDto.statusAcompanhamento = acompanhamento.statusAcompanhamento;
            ordemServicoGetDto.dataAtualizacaoAcompanhamento = acompanhamento.dataCriacao;
        }

        return ordemServicoGetDto;
    }

    private String ordemServicoClienteNome(OrdemServico ordemServico) {
        if ( ordemServico == null ) {
            return null;
        }
        Cliente cliente = ordemServico.cliente;
        if ( cliente == null ) {
            return null;
        }
        String nome = cliente.nome;
        if ( nome == null ) {
            return null;
        }
        return nome;
    }

    private String ordemServicoColaboradorNome(OrdemServico ordemServico) {
        if ( ordemServico == null ) {
            return null;
        }
        Colaborador colaborador = ordemServico.colaborador;
        if ( colaborador == null ) {
            return null;
        }
        String nome = colaborador.nome;
        if ( nome == null ) {
            return null;
        }
        return nome;
    }

    private String acompanhamentoColaboradorNome(Acompanhamento acompanhamento) {
        if ( acompanhamento == null ) {
            return null;
        }
        Colaborador colaborador = acompanhamento.colaborador;
        if ( colaborador == null ) {
            return null;
        }
        String nome = colaborador.nome;
        if ( nome == null ) {
            return null;
        }
        return nome;
    }
}
