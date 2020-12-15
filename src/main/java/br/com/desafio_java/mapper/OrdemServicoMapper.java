package br.com.desafio_java.mapper;

import br.com.desafio_java.dto.OrdemServicoGetDto;
import br.com.desafio_java.model.Acompanhamento;
import br.com.desafio_java.model.OrdemServico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrdemServicoMapper extends EntidadeMapper<OrdemServico, OrdemServicoGetDto> {

    OrdemServicoGetDto toDTO(OrdemServico entity);

    @Override
    OrdemServico toEntity(OrdemServicoGetDto usuarioDTO);

    @Mappings({
            @Mapping(source = "ordemServico.descricao", target = "descricao"),
            @Mapping(source = "ordemServico.cliente.nome", target = "nomeCliente"),
            @Mapping(source = "ordemServico.colaborador.nome", target = "nomeResponsavel"),
            @Mapping(source = "ordemServico.dataCriacao", target = "dataCriacao"),
            @Mapping(source = "ordemServico.dataAtualizacao", target = "dataAtualizacao"),
            @Mapping(source = "acompanhamento.colaborador.nome", target = "colaboradorAcompanhamento"),
            @Mapping(source = "acompanhamento.descricao", target = "descricaoAcompanhamento"),
            @Mapping(source = "acompanhamento.statusAcompanhamento", target = "statusAcompanhamento"),
            @Mapping(source = "acompanhamento.dataCriacao", target = "dataAtualizacaoAcompanhamento"),
    })
    OrdemServicoGetDto toDtoDadosOrdemServicoEAcompanhamento(OrdemServico ordemServico, Acompanhamento acompanhamento);
}