package br.com.desafio_java.mapper;

import br.com.desafio_java.dto.ColaboradorPostDto;
import br.com.desafio_java.model.Colaborador;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColaboradorMapper extends EntidadeMapper<Colaborador, ColaboradorPostDto> {

    ColaboradorPostDto toDTO(Colaborador entity);

    @Override
    Colaborador toEntity(ColaboradorPostDto usuarioDTO);
}

