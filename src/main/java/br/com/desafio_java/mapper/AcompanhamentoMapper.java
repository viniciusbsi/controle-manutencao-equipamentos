package br.com.desafio_java.mapper;

import br.com.desafio_java.dto.AcompanhamentoPostDto;
import br.com.desafio_java.dto.AcompanhamentoResponseDto;
import br.com.desafio_java.model.Acompanhamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AcompanhamentoMapper extends EntidadeMapper<Acompanhamento, AcompanhamentoPostDto> {

    AcompanhamentoPostDto toDTO(Acompanhamento entity);

    @Override
    Acompanhamento toEntity(AcompanhamentoPostDto acompanhamentoPostDto);

    @Mappings({
            @Mapping(source = "acompanhamento.id", target = "id"),
            @Mapping(source = "acompanhamento.dataCriacao", target = "dataCriacao"),
            @Mapping(source = "acompanhamento.statusAcompanhamento", target = "statusAcompanhamento"),
            @Mapping(source = "acompanhamento.descricao", target = "descricao"),
            @Mapping(source = "acompanhamento.colaborador.nome", target = "nomeColaborador"),
            @Mapping(source = "acompanhamento.ordemServico.id", target = "ordemServicoId"),
            @Mapping(source = "acompanhamento.colaborador.id", target = "colaboradorId"),
            @Mapping(source = "acompanhamento.ordemServico.equipamento", target = "equipamento"),
    })
    AcompanhamentoResponseDto toDtoResponse(Acompanhamento acompanhamento);

    @Mappings({
            @Mapping(source = "acompanhamento.id", target = "id"),
            @Mapping(source = "acompanhamento.dataCriacao", target = "dataCriacao"),
            @Mapping(source = "acompanhamento.statusAcompanhamento", target = "statusAcompanhamento"),
            @Mapping(source = "acompanhamento.descricao", target = "descricao"),
            @Mapping(source = "acompanhamento.colaborador.nome", target = "nomeColaborador"),
            @Mapping(source = "acompanhamento.ordemServico.id", target = "ordemServicoId"),
            @Mapping(source = "acompanhamento.colaborador.id", target = "colaboradorId"),
            @Mapping(source = "acompanhamento.ordemServico.equipamento", target = "equipamento"),
    })
    List<AcompanhamentoResponseDto> toDtoResponse(List<Acompanhamento> acompanhamento);
}