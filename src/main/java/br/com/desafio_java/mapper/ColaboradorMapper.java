package br.com.desafio_java.mapper;

import br.com.desafio_java.dto.ColaboradorPostDto;
import br.com.desafio_java.model.Colaborador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ColaboradorMapper extends EntidadeMapper<Colaborador, ColaboradorPostDto> {

    ColaboradorPostDto toDTO(Colaborador entity);

    @Override
    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Colaborador toEntity(ColaboradorPostDto usuarioDTO);
}

