package br.com.desafio_java.mapper;

import br.com.desafio_java.dto.OrdemServicoGetDto;
import br.com.desafio_java.model.Acompanhamento;
import br.com.desafio_java.model.OrdemServico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrdemServicoMapper extends EntidadeMapper<OrdemServico, OrdemServicoGetDto> {

    @Mappings({
            @Mapping(target = "nomeCliente", ignore = true),
            @Mapping(target = "nomeResponsavel", ignore = true),
            @Mapping(target = "descricaoAcompanhamento", ignore = true),
            @Mapping(target = "colaboradorAcompanhamento", ignore = true),
            @Mapping(target = "statusAcompanhamento", ignore = true),
            @Mapping(target = "dataAtualizacaoAcompanhamento", ignore = true)
    })
    OrdemServicoGetDto toDTO(OrdemServico entity);

    @Override
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "dataCriacao", ignore = true),
            @Mapping(target = "dataAtualizacao", ignore = true),
            @Mapping(target = "cliente", ignore = true),
            @Mapping(target = "colaborador", ignore = true),
            @Mapping(target = "equipamento", ignore = true),
    })
    OrdemServico toEntity(OrdemServicoGetDto usuarioDTO);

    @Mappings({
            @Mapping(source = "ordemServico.descricao", target = "descricao"),
            @Mapping(source = "ordemServico.cliente.nome", target = "nomeCliente"),
            @Mapping(source = "ordemServico.colaborador.nome", target = "nomeResponsavel"),
            @Mapping(source = "ordemServico.dataInicio", target = "dataInicio"),
            @Mapping(source = "ordemServico.dataFim", target = "dataFim"),
            @Mapping(source = "acompanhamento.colaborador.nome", target = "colaboradorAcompanhamento"),
            @Mapping(source = "acompanhamento.descricao", target = "descricaoAcompanhamento"),
            @Mapping(source = "acompanhamento.statusAcompanhamento", target = "statusAcompanhamento"),
            @Mapping(source = "acompanhamento.dataCriacao", target = "dataAtualizacaoAcompanhamento"),
    })
    OrdemServicoGetDto toDtoDadosOrdemServicoEAcompanhamento(OrdemServico ordemServico, Acompanhamento acompanhamento);
}